package org.lutra.cpa.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.lutra.cpa.response.get.Order;
import org.lutra.cpa.response.get.OrderResponse;
import org.lutra.cpa.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class OrdersCache
{
    final private static Logger log = LoggerFactory.getLogger("OrdersCache");
    final private static Cache<Integer, Order> cache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(3, TimeUnit.MINUTES)
            .build()
            ;
    public static void put(Order o)
    {
        assert(o != null);
        assert(o.getId() != 0); //TODO: Meh..

        cache.put(o.getId(), o);
    }

    public static Order get(final int id)
    {
        Order o = null;
        try
        {
            o = cache.get
            (id, new Callable<Order>()
                {
                    @Override
                    public Order call() throws Exception
                    {
                        log.info("Querying API for {}", id);
                        OrderResponse or =  OrderService.get(id);
                        if(or != null)
                            return or.uw();
                        else
                            return null; //TODO: Meh..
                    }
                }
            );
        }
        catch(Exception e)
        {
            log.error(e.toString(), e);
        }

        return o;
    }
}
