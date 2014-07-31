package org.lutra.cpa.handler;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Mt;
import org.lutra.cpa.model.OrderStatus;
import org.lutra.cpa.model.Pager;
import org.lutra.cpa.response.get.OrdersResponse;
import org.lutra.cpa.service.OrdersService;
import org.lutra.cpa.wrapper.MyHandlebars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class OrdersHandler implements HttpHandler
{
	final private static Logger log = LoggerFactory.getLogger(OrdersHandler.class);

	@Override
	public void handleHttpRequest(HttpRequest rx, HttpResponse tx, HttpControl ct) throws Exception
	{
		if(Helpers.authorize(rx))
			Mt.execute(new OrdersRunner(rx, tx, ct));
		else
			ct.nextHandler();
		log.info("leaving");
	}
	public static class OrdersRunner implements Runnable
	{
		HttpRequest rx;
		HttpResponse tx;
		HttpControl ct;

		public OrdersRunner(HttpRequest rx, HttpResponse tx, HttpControl ct)
		{
			this.rx = rx;
			this.tx = tx;
			this.ct = ct;
		}

		@Override
		public void run()
		{
			Map<String, Object> data = new HashMap<>();
			int pageSize = Helpers.queryGetInt(rx, "pageSize", 25);
			int page = Helpers.queryGetInt(rx, "page", 1);

			OrderStatus status = null;
			try
			{
				status = OrderStatus.valueOf(Helpers.queryGetString(rx, "status", ""));
			}
			catch(IllegalArgumentException e)
			{ /* dont care. that's perfectly fine. FIXME: however */ }
			OrdersResponse or = OrdersService.i().get(status, pageSize, page);
			Handlebars h = new MyHandlebars();
			try
			{
				data.put("back_url", URLEncoder.encode(rx.uri(), "UTF-8"));
			}
			catch(Exception e)
			{
				log.error(e.toString(), e);
			}
			Pager pager = or.getPager();
			data.put("raw_back_url", rx.uri());
			data.put("pager", pager);
			String prev_page_link = String.format("/orders?pageSize=%s&page=%s&status=%s", pageSize, pager.previousPage(), status == null ? "" : status.name());
			String next_page_link = String.format("/orders?pageSize=%s&page=%s&status=%s", pageSize, pager.nextPage(), status == null ? "" : status.name());
			data.put("prev_page_link", prev_page_link);
			data.put("next_page_link", next_page_link);
			data.put("orders", or);
			data.put("order_status", OrderStatus.values());
			Context c = Context
				.newBuilder(data)
				.resolver(FieldValueResolver.INSTANCE, MapValueResolver.INSTANCE, MethodValueResolver.INSTANCE)
				.build();
			try
			{
				Template t = h.compile("orders");
				String s = t.apply(c);
				tx.content(s);
			}
			catch(Exception e)
			{
				log.error(e.toString(), e);
			}
			log.info("working");
			tx.status(200);
			tx.end();
		}
	}
}
