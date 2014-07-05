package org.lutra.cpa.service;

import org.lutra.cpa.Config;
import org.lutra.cpa.Main;
import org.lutra.cpa.model.OrderStatus;
import org.lutra.cpa.response.get.OrdersResponse;

public class OrdersService
{
    public static OrdersResponse get()
    {
        return get(null, 50, 1);
    }

    public static OrdersResponse get(OrderStatus status, int pageSize, int page)
    {
        int ps = pageSize > 50 || pageSize < 1 ? 50 : pageSize;
        int p = page < 1 || page > 100 ? 1 : page;
        String path = String.format("/campaigns/%s/orders.json?pageSize=%s&page=%s", Config.campaignId, ps, p);
        if(status != null)
            path += "&status=" + status.name();
        String json = Market.request(path);
        return Main.g.fromJson(json, OrdersResponse.class);
    }
}
