package org.lutra.cpa.wrapper;

import org.lutra.cpa.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

/**
 * 08.07.2014 at 14:30
 * AuthorizedHttpHandler of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class AuthorizedHttpHandler implements HttpHandler
{
	final private HttpHandler h;
	final private static Logger log = LoggerFactory.getLogger(AuthorizedHttpHandler.class);
	public AuthorizedHttpHandler(HttpHandler h)
	{
		this.h = h;
	}
	@Override
	public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) throws Exception
	{
		if(request.hasHeader("Authorized") && request.header("Authorized").equals(Config.oauth_token))
		{
			log.info("Authorized");
			h.handleHttpRequest(request, response, control);
		}
		else
		{
			log.info("Unauthorized request to {}", request.uri());
			response.status(403);
			response.end();
		}
	}
}
