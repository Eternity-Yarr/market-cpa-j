package org.lutra.cpa.handler;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import org.lutra.cpa.Mt;
import org.lutra.cpa.cache.SessionsCache;
import org.lutra.cpa.repository.BitrixAuthorizationRepository;
import org.lutra.cpa.service.AuthorizationService;
import org.lutra.cpa.service.CookieService;
import org.lutra.cpa.wrapper.MyHandlebars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class LoginHandler implements HttpHandler
{
	final private static Logger log = LoggerFactory.getLogger(LoginHandler.class);

	@Override
	public void handleHttpRequest(HttpRequest rx, HttpResponse tx, HttpControl ct) throws Exception
	{
		Mt.execute(new LoginRunner(rx, tx, ct));
		log.info("leaving");
	}
	public static class LoginRunner implements Runnable
	{
		HttpRequest rx;
		HttpResponse tx;
		HttpControl ct;

		public LoginRunner(HttpRequest rx, HttpResponse tx, HttpControl ct)
		{
			this.rx = rx;
			this.tx = tx;
			this.ct = ct;
		}

		@Override
		public void run()
		{
			log.info("working");
			Map<String, Object> data = new HashMap<>();
			boolean redirect = false;
			String target = "/orders";
			String token = rx.cookieValue("CPA");
			int authorizedAs = -1;
			if(rx.method().equals("POST"))
			{
				String login = rx.postParam("inputEmail");
				String password = rx.postParam("inputPassword");
				target = rx.postParam("back_url");
				authorizedAs = AuthorizationService.authorized(login, password);
			}
			else if(token != null)
				authorizedAs = AuthorizationService.authorized(token);

			if(authorizedAs > 0)
			{
				token = BitrixAuthorizationRepository.i().generateToken();
				SessionsCache.put(token, authorizedAs);
				tx.cookie(CookieService.i().setCookie("CPA", token));
				redirect = true;
			}
			else
				data.put("error",true);
			if(redirect)
			{
				// Status should be something like 303 because we need to change request type to GET from whatever
				tx.status(303);
				tx.header("Location", target == null ? "/orders" : target);
			}
			else
			{
				Handlebars h = new MyHandlebars();
				Context c = Context
					.newBuilder(data)
					.resolver(FieldValueResolver.INSTANCE, MapValueResolver.INSTANCE, MethodValueResolver.INSTANCE)
					.build();
				try
				{
					Template t = h.compile("login");
					String s = t.apply(c);
					tx.content(s);
				}
				catch(Exception e)
				{
					log.error(e.toString(), e);
				}
				tx.status(200);
			}
			tx.end();
		}
	}
}
