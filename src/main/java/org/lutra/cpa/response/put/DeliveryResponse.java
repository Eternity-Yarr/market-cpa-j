package org.lutra.cpa.response.put;

import org.lutra.cpa.model.Delivery;

/**
 * Structure of PUT /campaigns/{campaignId}/orders/{orderId}/delivery response
 *
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/put-campaigns-id-orders-id-delivery.xml">reference</a>
 */
public class DeliveryResponse
{
	public Delivery delivery;
}
