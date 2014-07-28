package org.lutra.cpa.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

public class NotFoundHandler implements HttpHandler
{
	private static Logger log = LoggerFactory.getLogger("404");
	@Override
	public void handleHttpRequest(HttpRequest rx, HttpResponse tx, HttpControl ct) throws Exception
	{
		log.info(rx.uri());
		// change request type to GET from whatever
		tx.status(303);
		tx.header("Location", "/");
		tx.end();

		log.info("leaving");
	}
}
