package org.lutra.cpa.handler;

import org.lutra.cpa.Helpers;
import org.lutra.cpa.Main;
import org.lutra.cpa.Mt;
import org.lutra.cpa.request.post.OrderStatusRequest;
import org.lutra.cpa.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

/**
 * 01.08.2014 at 15:06
 * OrderStatusHandler of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class OrderStatusHandler implements HttpHandler
{
	final private static Logger log = LoggerFactory.getLogger(OrderStatusHandler.class);

	@Override
	public void handleHttpRequest(HttpRequest rx, HttpResponse tx, HttpControl ct) throws Exception
	{
		Mt.execute(new OrderStatusRunner(rx, tx, ct));
		log.info("leaving");
	}
	public static class OrderStatusRunner implements Runnable
	{
		HttpRequest rx;
		HttpResponse tx;
		HttpControl ct;

		public OrderStatusRunner(HttpRequest rx, HttpResponse tx, HttpControl ct)
		{
			this.rx = rx;
			this.tx = tx;
			this.ct = ct;
		}

		@Override
		public void run()
		{
			log.info("working");
			if(rx.method().equals("POST") && Helpers.authorize(rx))
			{
				String raw_post = rx.body();
				OrderStatusRequest osr;
				try
				{
					osr = Main.g.fromJson(raw_post, OrderStatusRequest.class);
					if(osr != null && osr.uw() != null)
					{
						tx.status(200);
						log.info("Updating status of {} to {}", osr.uw().id, osr.uw().substatus);
						OrderService.updateStatus(osr.uw().id, osr.uw().status, osr.uw().substatus);
					}
					else
					{
						log.info("JSON looks invalid:" + raw_post);
						tx.content("Json looks invalid: " + raw_post);
						tx.status(400);
					}
				}
				catch(Exception e)
				{
					log.error(e.toString(), e);
				}
			}
			else
			{
				log.info("Wrong request method. Expecting POST");
				tx.status(400);
			}
			tx.end();
		}
	}
}
