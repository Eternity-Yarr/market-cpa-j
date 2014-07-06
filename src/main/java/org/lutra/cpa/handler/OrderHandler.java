package org.lutra.cpa.handler;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.cache.OrdersCache;
import org.lutra.cpa.model.OrderStatus;
import org.lutra.cpa.response.get.Order;
import org.lutra.cpa.service.OrderStatusService;
import org.lutra.cpa.wrapper.MyHandlebars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class OrderHandler implements HttpHandler
{
    private static Logger log = LoggerFactory.getLogger("Order");
    @Override
    public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) throws Exception
    {
        new Thread(new OrderRunner(request,response,control)).start();
        log.info("leaving");
    }
    public static class OrderRunner implements Runnable
    {
        HttpRequest rx;
        HttpResponse tx;
        HttpControl ct;

        public OrderRunner(HttpRequest rx, HttpResponse tx, HttpControl ct)
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
            int id = Helpers.queryGetInt(rx, "id", 0); //TODO: default? i dont think so. Spit an error.
            String back_url = Helpers.queryGetString(rx, "back_url", "/orders");


            Order o = OrdersCache.get(id);
            Handlebars h = new MyHandlebars();

            data.put("back_url", back_url);
            data.put("order", o);
            data.put("order_status", OrderStatus.values());
            data.put("status_transitions", OrderStatusService.possibleTransitions(o.getStatus()));
            data.put("cancellation_reasons", OrderStatusService.possibleCancellationReasons(o.getStatus()));
            Context c = Context
                    .newBuilder(data)
                    .resolver(FieldValueResolver.INSTANCE, MapValueResolver.INSTANCE, MethodValueResolver.INSTANCE)
                    .build();
            try
            {
                Template t = h.compile("order");
                String s = t.apply(c);
                tx.content(s);
            }
            catch(Exception e)
            {
                log.error(e.toString(), e);
            }
            tx.status(200);
            tx.end();
        }
    }
}
