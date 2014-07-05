package org.lutra.cpa.handler;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.model.OrderStatus;
import org.lutra.cpa.response.get.OrdersResponse;
import org.lutra.cpa.service.OrdersService;
import org.lutra.cpa.wrapper.MyHandlebars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class OrdersHandler implements HttpHandler
{
    private static Logger log = LoggerFactory.getLogger("Orders");
    @Override
    public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) throws Exception
    {
        new Thread(new OrdersRunner(request,response,control)).start();
        log.info("leaving");
    }
    public static class OrdersRunner implements Runnable
    {
        HttpRequest rx;
        HttpResponse tx;
        HttpControl ct;

        public OrdersRunner(HttpRequest rx, HttpResponse tx, HttpControl ct)
        {
            this.rx = rx;
            this.tx = tx;
            this.ct = ct;
        }

        @Override
        public void run()
        {
            Map<String, Object> data = new HashMap<>();
            int pageSize = Helpers.queryGetInt(rx, "pageSize", 25);
            int page = Helpers.queryGetInt(rx, "page", 1);

            OrderStatus status = null;
            try
            {
                status = OrderStatus.valueOf(Helpers.queryGetString(rx, "status", ""));
            }
            catch(IllegalArgumentException e){ /* dont care. that's perfectly fine. FIXME: however */ }

            OrdersResponse or = OrdersService.get(status, pageSize, page);
            Handlebars h = new MyHandlebars();
            data.put("orders", or);
            data.put("order_status", OrderStatus.values());
            Context c = Context
                    .newBuilder(data)
                    .resolver(FieldValueResolver.INSTANCE, MapValueResolver.INSTANCE, MethodValueResolver.INSTANCE)
                    .build();
            try
            {
                Template t = h.compile("orders");
                String s = t.apply(c);
                tx.content(s);
            }
            catch(Exception e)
            {
                log.error(e.toString(), e);
            }
            log.info("working");
            tx.status(200);
            tx.end();
        }
    }
}
