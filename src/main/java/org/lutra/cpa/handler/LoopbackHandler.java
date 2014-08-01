package org.lutra.cpa.handler;

import org.lutra.cpa.Helpers;
import org.lutra.cpa.model.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import java.util.HashMap;
import java.util.Map;

//TODO: Delete me
public class LoopbackHandler implements HttpHandler
{
	final private static Logger log = LoggerFactory.getLogger(LoopbackHandler.class);

	@Override
	public void handleHttpRequest(HttpRequest rx, HttpResponse tx, HttpControl ct) throws Exception
	{
		log.info(rx.uri());
		String body = rx.body();
		tx.header("Content-Type", "text/html");
		log.info(String.format("Method : %s", rx.method()));
		log.info("---");
		log.info(String.format("%s", body));
		Map<String, String> post = new HashMap<>();
		for(String key : rx.postParamKeys())
			post.put(key, rx.postParam(key));
		Delivery d = Helpers.castMap(post, "delivery", "-", Delivery.class);
		System.out.println(d);
		tx.status(200);
		tx.end();
		log.info("leaving");
	}
}
