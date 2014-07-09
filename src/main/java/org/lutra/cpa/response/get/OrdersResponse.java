package org.lutra.cpa.response.get;

import org.lutra.cpa.model.Order;
import org.lutra.cpa.model.Pager;

import java.util.List;

/**
 * Structure of GET /campaigns/{campaignId}/orders response
 *
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/get-campaigns-id-orders.xml">reference</a>
 */
public class OrdersResponse
{
	Pager pager;
	List<Order> orders;

	public Pager getPager()
	{
		return pager;
	}

	public List<Order> getOrders()
	{
		return orders;
	}
}
