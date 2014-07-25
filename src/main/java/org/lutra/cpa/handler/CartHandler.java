package org.lutra.cpa.handler;

import org.lutra.cpa.Helpers;
import org.lutra.cpa.Main;
import org.lutra.cpa.request.post.CartRequest;
import org.lutra.cpa.response.CartResponse;
import org.lutra.cpa.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

public class CartHandler implements HttpHandler
{
	private static Logger log = LoggerFactory.getLogger(CartHandler.class);
	@Override
	public void handleHttpRequest(HttpRequest rx, HttpResponse tx, HttpControl ct) throws Exception
	{
		new Thread(new CartRunner(rx, tx, ct)).start();
		log.info("leaving");
	}
	public static class CartRunner implements Runnable
	{
		HttpRequest rx;
		HttpResponse tx;
		HttpControl ct;

		public CartRunner(HttpRequest rx, HttpResponse tx, HttpControl ct)
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
				CartRequest cr = null;
				try
				{
					cr = Main.g.fromJson(raw_post, CartRequest.class);
				}
				catch(Exception e)
				{
					log.error(e.toString(), e);
				}
				if(cr == null)
					Helpers.error_500(tx, "Cannot de-serialize JSON");
				else
				{
					CartResponse response = CartService.process(cr);
					Helpers.ok_200(tx, response);
				}
			}
			else
			{
				// Wrong method
			}

			tx.status(200);
			tx.end();
		}
	}
}
