package org.lutra.cpa.handler;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Mt;
import org.lutra.cpa.model.HistoryEntry;
import org.lutra.cpa.model.OrderStatus;
import org.lutra.cpa.service.HistoryService;
import org.lutra.cpa.wrapper.MyHandlebars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 01.08.2014 at 17:32
 * HistoryHandler of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class HistoryHandler implements HttpHandler
{
	final private static Logger log = LoggerFactory.getLogger(HistoryHandler.class);

	@Override
	public void handleHttpRequest(HttpRequest rx, HttpResponse tx, HttpControl ct) throws Exception
	{
		if(Helpers.authorize(rx))
			Mt.execute(new HistoryRunner(rx, tx, ct));
		else
			ct.nextHandler();
		log.info("leaving");
	}

	public static class HistoryRunner implements Runnable
	{
		HttpRequest rx;
		HttpResponse tx;
		HttpControl ct;

		public HistoryRunner(HttpRequest rx, HttpResponse tx, HttpControl ct)
		{
			this.rx = rx;
			this.tx = tx;
			this.ct = ct;
		}

		@Override
		public void run()
		{
			Map<String,Object> data = new HashMap<>();
			List<HistoryEntry> he = HistoryService.i().getRecent();
			data.put("order_status", OrderStatus.values());
			data.put("history", he);
			Context c = Context
				.newBuilder(data)
				.resolver(FieldValueResolver.INSTANCE, MapValueResolver.INSTANCE, MethodValueResolver.INSTANCE)
				.build();
			Handlebars h = new MyHandlebars();
			try
			{
				Template t = h.compile("history");
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
