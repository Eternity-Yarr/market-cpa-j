package org.lutra.cpa.handler;

import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

/**
 * 01.08.2014 at 15:07
 * OrderAcceptHandler of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class OrderAcceptHandler implements HttpHandler
{
	@Override
	public void handleHttpRequest(HttpRequest rx, HttpResponse tx, HttpControl ct) throws Exception
	{
		//TODO: STUB
		tx.status(501);
		tx.end();
	}
}
