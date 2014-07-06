package org.lutra.cpa.handler;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Main;
import org.lutra.cpa.cache.OrdersCache;
import org.lutra.cpa.cache.OutletsCache;
import org.lutra.cpa.model.Order;
import org.lutra.cpa.model.OrderStatus;
import org.lutra.cpa.request.post.CartRequest;
import org.lutra.cpa.response.CartResponse;
import org.lutra.cpa.service.CartService;
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

public class CartHandler implements HttpHandler
{
    private static Logger log = LoggerFactory.getLogger("Cart");
    @Override
    public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) throws Exception
    {
        new Thread(new CartRunner(request,response,control)).start();
        log.info("leaving");
    }
    public static class CartRunner implements Runnable
    {
        HttpRequest rx;
        HttpResponse tx;
        HttpControl ct;

        public CartRunner(HttpRequest rx, HttpResponse tx, HttpControl ct)
        {
            this.rx = rx;
            this.tx = tx;
            this.ct = ct;
        }

        @Override
        public void run()
        {
            log.info("working");

            if(rx.method().equals("POST"))
            {
                String raw_post = rx.body();
                CartRequest cr = null;
                try
                {
                    cr = Main.g.fromJson(raw_post, CartRequest.class);
                }
                catch(Exception e)
                {
                    log.error(e.toString(), e);
                }
                if(cr == null)
                    Helpers.error_500(tx, "Cannot deserialize JSON");
                else
                {
                    CartResponse response = CartService.process(cr);
                    Helpers.ok_200(tx, response);
                }
            }
            else
            {
                // Wrong method
            }



            tx.status(200);
            tx.end();
        }
    }
}
