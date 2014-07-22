package org.lutra.cpa.handler;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import org.lutra.cpa.Config;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Main;
import org.lutra.cpa.cache.OrdersCache;
import org.lutra.cpa.cache.OutletsCache;
import org.lutra.cpa.model.*;
import org.lutra.cpa.model.deliveries.DeliveryDelivery;
import org.lutra.cpa.model.deliveries.DeliveryPickup;
import org.lutra.cpa.model.deliveries.DeliveryPost;
import org.lutra.cpa.response.get.OrderResponse;
import org.lutra.cpa.response.put.DeliveryResponse;
import org.lutra.cpa.service.Market;
import org.lutra.cpa.service.OrderStatusService;
import org.lutra.cpa.service.OutletsService;
import org.lutra.cpa.wrapper.MyHandlebars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChangeDeliveryHandler implements HttpHandler
{
	private static Logger log = LoggerFactory.getLogger("Delivery");
	@Override
	public void handleHttpRequest(HttpRequest rx, HttpResponse tx, HttpControl ct) throws Exception
	{
		new Thread(new DeliveryRunner(rx, tx, ct)).start();
		log.info("leaving");
	}
	public static class DeliveryRunner implements Runnable
	{
		HttpRequest rx;
		HttpResponse tx;
		HttpControl ct;

		public DeliveryRunner(HttpRequest rx, HttpResponse tx, HttpControl ct)
		{
			this.rx = rx;
			this.tx = tx;
			this.ct = ct;
		}

		@Override
		public void run()
		{
			log.info("Working {}", rx.method());
			if(rx.method().equals("POST"))
			{
				Map<String, String> post = new HashMap<>();
				for(String key : rx.postParamKeys())
					post.put(key, rx.postParam(key));
				Delivery dlv = Helpers.castMap(post, "delivery", "-", Delivery.class);
				DeliveryResponse dr = new DeliveryResponse();
				String json = "";
				switch(dlv.getType())
				{
					case DELIVERY:
						dr.delivery = Helpers.castMap(post, "delivery", "-", DeliveryDelivery.class);
						break;
					case PICKUP:
						dr.delivery = Helpers.castMap(post, "delivery", "-", DeliveryPickup.class);
						break;
					case POST:
						dr.delivery = Helpers.castMap(post, "delivery", "-", DeliveryPost.class);
						break;
				}
				try
				{
					int id = Integer.parseInt(post.get("order-id"));
					String path = String.format("/campaigns/%s/orders/%s/delivery.json", Config.campaignId, id);
					json = Main.g.toJson(dr);
					String or_json = Market.putRequest(path, json);

					OrderResponse or = Main.g.fromJson(or_json, OrderResponse.class);
					if(or != null)
					{
						log.info("Replacing order {} in cache", or.uw().getId());
						OrdersCache.put(or.uw());
					}
					else
					{
						log.error("Malformed order response:");
						log.error(or_json);
					}
					tx.status(307);
					tx.header("Location",String.format("/order?id=%s",id));
				}
				catch(Exception e)
				{
					tx.status(500);
					tx.content(e.toString());
					log.error(e.toString(), e);
				}
				System.out.println(json);
			}
			else
			{
				Map<String, Object> data = new HashMap<>();
				int id = Helpers.queryGetInt(rx, "id", 0); //TODO: default? i dont think so. Spit an error.
				String back_url = Helpers.queryGetString(rx, "back_url", "/orders");
				Order o = OrdersCache.get(id);

				Handlebars h = new MyHandlebars();
				data.put("raw_back_url", back_url);
				try
				{
					data.put("back_url", URLEncoder.encode(back_url, "UTF-8"));
				}
				catch(Exception e)
				{
					log.error(e.toString(), e);
				}
				if(o != null)
				{
					List<Outlet> or = OutletsService.get();
					data.put("order", o);
					data.put("delivery_type", DeliveryType.values());
					data.put("order_status", OrderStatus.values());
					data.put("outlets", or);
					if(o.getDelivery().is_pickup())
						data.put("outlet", OutletsCache.get(o.getDelivery().getOutletId()));
					Set<OrderStatus> status_transitions = OrderStatusService.possibleTransitions
						(
							o.getStatus(),
							o.getDelivery().getType()
						);
					if(status_transitions.contains(OrderStatus.CANCELLED))
						data.put("cancellable", true);
					status_transitions.remove(OrderStatus.CANCELLED);

					data.put("status_transitions", status_transitions);
					data.put("cancellation_reasons", OrderStatusService.possibleCancellationReasons(o.getStatus()));
				}
				Context c = Context
					.newBuilder(data)
					.resolver(FieldValueResolver.INSTANCE, MapValueResolver.INSTANCE, MethodValueResolver.INSTANCE)
					.build();
				try
				{
					Template t = h.compile("delivery");
					String s = t.apply(c);
					tx.content(s);
				}
				catch(Exception e)
				{
					log.error(e.toString(), e);
				}
				tx.status(200);
			}
			tx.end();
		}
	}
}
