package org.lutra.cpa.request;

import org.lutra.cpa.model.*;

import java.util.List;

/**
 * Structure of POST /order/stats request
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/post-order-status.xml">reference</a>
 */
public class OrderStatus
{
    int id;
    org.lutra.cpa.model.OrderStatus status;
    OrderSubstatus substatus;
    String creationDate; //TODO: Write appropriate deserializer
    int itemsTotal;
    int total;
    PaymentType paymentType;
    PaymentMethod paymentMethod;
    boolean fake;
    String notes;
    List<Item> items;
    Delivery delivery;
    Buyer buyer;
}
