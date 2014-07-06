package org.lutra.cpa;

import org.lutra.cpa.handler.CartHandler;
import org.lutra.cpa.handler.NotFoundHandler;
import org.lutra.cpa.handler.OrderHandler;
import org.lutra.cpa.handler.OrdersHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;

public class Ws
{
	public static WebServer ws;
	final static Logger log = LoggerFactory.getLogger("WS");

	public static void run()
	{
		ws = WebServers.createWebServer(9888);
		ws
            .add(new StaticFileHandler("./var"))
		;
		ws
			.add("/orders", new OrdersHandler())
            .add("/order", new OrderHandler())
            .add("/cart", new CartHandler())
			.add(new NotFoundHandler())
		;
		try
		{
			log.info("Starting web server listening at {}", ws.getPort());
			ws.start().get();
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}
	}
}
