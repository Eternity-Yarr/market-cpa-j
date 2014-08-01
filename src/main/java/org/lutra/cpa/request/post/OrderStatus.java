package org.lutra.cpa.request.post;

import org.lutra.cpa.model.*;

import java.util.List;

/**
 * Structure of POST /order/stats request
 *
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/post-order-status.xml">reference</a>
 */
public class OrderStatus
{
	public int id;
	public org.lutra.cpa.model.OrderStatus status;
	public OrderSubstatus substatus;
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
