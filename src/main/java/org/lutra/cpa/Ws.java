package org.lutra.cpa;

import org.lutra.cpa.handler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;

public class Ws
{
	static
	{
		//for localhost testing only
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier
			(
				new javax.net.ssl.HostnameVerifier()
				{
					public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession)
					{
						return hostname.equals("localhost");
					}
				}
			);
	}

	public static WebServer ws;
	final static Logger log = LoggerFactory.getLogger(Ws.class);

	public static void run()
	{
		ws = WebServers.createWebServer(Config.i().listening_port);
		if(Config.i().disable_ssl)
			log.info("SSL disabled in config");
		else
			try
			{
				// To generate new certificate use smth like:
				// $ keytool -genkey -alias webCrt -keystore keystore -keysize 1024 -validity 3600
				ws.setupSsl(Ws.class.getResourceAsStream("keystore"), "password");
			}
			catch(Exception e)
			{
				log.error("Certificate not found. Not going to SSL", e);
				ws = WebServers.createWebServer(Config.i().listening_port);
			}
		ws
			.add(new StaticFileHandler("./var"))
		;
		ws
			.add("/orders", new OrdersHandler())
			.add("/order", new OrderHandler())
			.add("/cart", new CartHandler())
			.add("/", new LoginHandler())
			.add("/change_status", new ChangeStatusHandler())
			.add("/change_delivery", new ChangeDeliveryHandler())
			.add("/loopback", new LoopbackHandler())  //TODO: Delete me
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
