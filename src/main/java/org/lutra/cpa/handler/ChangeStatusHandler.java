package org.lutra.cpa.handler;

import org.lutra.cpa.Config;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Main;
import org.lutra.cpa.Mt;
import org.lutra.cpa.cache.APICache;
import org.lutra.cpa.cache.OrdersCache;
import org.lutra.cpa.model.OrderStatus;
import org.lutra.cpa.model.OrderSubstatus;
import org.lutra.cpa.request.put.Status;
import org.lutra.cpa.request.put.StatusRequest;
import org.lutra.cpa.response.get.OrderResponse;
import org.lutra.cpa.service.HistoryService;
import org.lutra.cpa.service.MarketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

/**
 * 08.07.2014 at 11:27
 * ChangeStatusHandler of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class ChangeStatusHandler implements HttpHandler
{
	final private static Logger log = LoggerFactory.getLogger(ChangeStatusHandler.class);
	@Override
	public void handleHttpRequest(HttpRequest rx, HttpResponse tx, HttpControl ct) throws Exception
	{
		if(Helpers.authorize(rx))
			Mt.execute(new ChangeStatus(rx, tx, ct));
		else
			ct.nextHandler();
		log.info("leaving");
	}
	public static class ChangeStatus implements Runnable
	{
		HttpRequest rx;
		HttpResponse tx;
		HttpControl ct;

		public ChangeStatus(HttpRequest rx, HttpResponse tx, HttpControl ct)
		{
			this.rx = rx;
			this.tx = tx;
			this.ct = ct;
		}

		@Override
		public void run()
		{
			{
				String back_url = Helpers.queryGetString(rx, "back_url", "/orders");
				int id = Helpers.queryGetInt(rx, "id", -1);
				String path = String.format("/campaigns/%s/orders/%s/status.json", Config.i().campaignId, id);
				OrderStatus status = null;
				OrderSubstatus substatus = null;
				String str_status = rx.queryParam("status");
				String str_substatus = rx.queryParam("substatus");
				try
				{
					status = OrderStatus.valueOf(str_status);
					if(status == OrderStatus.CANCELLED && str_substatus != null)
						substatus = OrderSubstatus.valueOf(str_substatus);
				}
				catch(Exception e)
				{
					log.info("Cannot recognize status or substatus codes {} {}", str_status, str_substatus);
				}
				StatusRequest sr = new StatusRequest();
				boolean has_error = id == -1;
				Status s = new Status();
				if(status != null && status != OrderStatus.CANCELLED)
					s.setStatus(status);
				else if(status != null && substatus != null)
				{
					s.setStatus(status);
					s.setSubstatus(substatus);
				}
				else
					has_error = true;
				sr.setStatus(s);
				if(!has_error)
				{
					String body = Main.g.toJson(sr);
					String or_json = MarketService.i().putRequest(path, body);
					OrderResponse or = Main.g.fromJson(or_json, OrderResponse.class);
					if(or != null)
					{
						if(or.hasError())
						{
							log.info("Got an error from API:");
							log.info("[{}] {}", or.error.code, or.error.message);
						}
						else
						{
							String message = String.format("Изменен статус заказа. Новый статус %s (%s)", status.desc, status.name());
							if(substatus != null)
								message += String.format(" - %s (%s)", substatus.desc, substatus.name());
							HistoryService.i().add(Helpers.currentSession(rx), id, message);
							log.info("Replacing order {} in cache", or.uw().getId());
							OrdersCache.put(or.uw());
							APICache.invalidate();
						}
					}
					else
					{
						log.error("Malformed order response:");
						log.error(or_json);
					}
					tx.status(307);
					tx.header("Location", back_url);
				}
				tx.end();
			}
		}
	}
}
