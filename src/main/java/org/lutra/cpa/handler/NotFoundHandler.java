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
    public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) throws Exception
    {
        response.content("NOT FOUND");
        response.status(404);
        response.end();
        log.info("leaving");
    }
}
