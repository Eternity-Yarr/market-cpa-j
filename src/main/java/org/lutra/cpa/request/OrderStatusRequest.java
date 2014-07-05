package org.lutra.cpa.request;

/**
 * Structure of POST /order/stats request
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/post-order-status.xml">reference</a>
 */
public class OrderStatusRequest
{
    OrderStatus order;
    public OrderStatus uw(){return order;}
}
