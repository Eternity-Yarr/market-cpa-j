package org.lutra.cpa.handler;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Mt;
import org.lutra.cpa.cache.OrdersCache;
import org.lutra.cpa.cache.OutletsCache;
import org.lutra.cpa.model.HistoryEntry;
import org.lutra.cpa.model.Order;
import org.lutra.cpa.model.OrderStatus;
import org.lutra.cpa.service.HistoryService;
import org.lutra.cpa.service.OrderStatusService;
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

public class OrderHandler implements HttpHandler
{
	final private static Logger log = LoggerFactory.getLogger(OrderHandler.class);
	@Override
	public void handleHttpRequest(HttpRequest rx, HttpResponse tx, HttpControl ct) throws Exception
	{
		if(Helpers.authorize(rx))
			Mt.execute(new OrderRunner(rx, tx, ct));
		else
			ct.nextHandler();
		log.info("leaving");
	}

	public static class OrderRunner implements Runnable
	{
		HttpRequest rx;
		HttpResponse tx;
		HttpControl ct;

		public OrderRunner(HttpRequest rx, HttpResponse tx, HttpControl ct)
		{
			this.rx = rx;
			this.tx = tx;
			this.ct = ct;
		}

		@Override
		public void run()
		{
			log.info("working");
			Map<String, Object> data = new HashMap<>();
			int id = Helpers.queryGetInt(rx, "id", 0); //TODO: default? i dont think so. Spit an error.
			String back_url = Helpers.queryGetString(rx, "back_url", "/orders");
			Order o = OrdersCache.get(id);
			List<HistoryEntry> history = HistoryService.i().get(id);
			data.put("history", history);
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
				data.put("order", o);
				data.put("order_status", OrderStatus.values());
				if(o.getDelivery().is_pickup())
					data.put("outlet", OutletsCache.get(o.getDelivery().getOutletId()));
				Set<OrderStatus> status_transitions = OrderStatusService.i().possibleTransitions
					(
						o.getStatus(),
						o.getDelivery().getType()
					);
				if(status_transitions.contains(OrderStatus.CANCELLED))
					data.put("cancellable", true);
				status_transitions.remove(OrderStatus.CANCELLED);

				data.put("status_transitions", status_transitions);
				data.put("cancellation_reasons", OrderStatusService.i().possibleCancellationReasons(o.getStatus()));
			}
			Context c = Context
				.newBuilder(data)
				.resolver(FieldValueResolver.INSTANCE, MapValueResolver.INSTANCE, MethodValueResolver.INSTANCE)
				.build();
			Handlebars h = new MyHandlebars();
			try
			{
				Template t = h.compile("order");
				String s = t.apply(c);
				tx.content(s);
			}
			catch(Exception e)
			{
				log.error(e.toString(), e);
			}
			tx.status(200);
			tx.end();
		}
	}
}
