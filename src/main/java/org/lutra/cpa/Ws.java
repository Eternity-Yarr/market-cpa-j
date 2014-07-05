package org.lutra.cpa;

import org.lutra.cpa.handler.NotFoundHandler;
import org.lutra.cpa.handler.OrdersHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;

/**
 * 01.07.2014 at 10:01
 * Ws of m.c2y project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
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
