package org.lutra.cpa.response.get;

import org.lutra.cpa.model.Order;
import org.lutra.cpa.response.ErrorResponse;

/**
 * Structure of GET /campaigns/{campaignId}/orders/{orderId}
 *
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/get-campaigns-id-orders-id.xml">reference</a>
 */
public class OrderResponse extends ErrorResponse
{
	Order order = null;
	public Order uw()
	{
		return order;
	}
}
