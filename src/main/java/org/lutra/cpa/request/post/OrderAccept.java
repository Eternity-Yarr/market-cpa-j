package org.lutra.cpa.request.post;

import org.lutra.cpa.model.*;

import java.util.List;

/**
 * Structure of POST /order/accept request
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/post-order-accept.xml">reference</a>
 */
public class OrderAccept
{
    int id;
    Currency currency;
    PaymentType paymentType;
    PaymentMethod paymentMethod;
    boolean fake;
    List<Item> items;
    String notes;
    Delivery delivery;
}
