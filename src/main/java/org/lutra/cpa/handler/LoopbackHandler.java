package org.lutra.cpa.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import java.util.List;
//TODO: Delete me
public class LoopbackHandler implements HttpHandler
{
    private static Logger log = LoggerFactory.getLogger("Loopback");
    @Override
    public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) throws Exception
    {
        log.info(request.uri());
				String body = request.body();
				response.header("Content-Type", "text/html");
				log.info(String.format("Method : %s", request.method()));
				log.info("---");
        log.info(String.format("%s", body));
				response.status(200);
        response.end();
        log.info("leaving");
    }
}
