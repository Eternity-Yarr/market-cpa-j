package org.lutra.cpa.response.get;

import org.lutra.cpa.model.*;

import java.util.List;

/**
 * Structure of GET /campaigns/{campaignId}/orders/{orderId} response
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/get-campaigns-id-orders-id.xml">reference</a>
 */
public class Order
{
    int id;
    OrderStatus status;
    OrderSubstatus substatus;
    String creationDate; //TODO: approppriate deserializer
    Currency currency;
    int itemsTotal;
    double total;
    PaymentType paymentType;
    PaymentMethod paymentMethod;
    boolean fake;
    String notes;
    List<Item> items;
    Delivery delivery;
    Buyer buyer;

    public int getId()
    {
        return id;
    }

    public OrderStatus getStatus()
    {
        return status;
    }
}
