package org.lutra.cpa.request.put;

import org.lutra.cpa.model.Delivery;

/**
 * Structure of PUT /campaigns/{campaignId}/orders/{orderId}/delivery request
 *
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/put-campaigns-id-orders-id-delivery.xml">reference</a>
 */
public class DeliveryRequest
{
	Delivery delivery;
}
