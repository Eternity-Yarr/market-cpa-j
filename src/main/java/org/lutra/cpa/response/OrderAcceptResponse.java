package org.lutra.cpa.response;

/**
 * Structure of POST /order/accept response
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/post-order-accept.xml">reference</a>
 */
public class OrderAcceptResponse
{
    OrderAccept order;
    public OrderAccept uw(){return order;}
}
