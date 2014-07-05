package org.lutra.cpa.request.put;

import org.lutra.cpa.model.OrderStatus;
import org.lutra.cpa.model.OrderSubstatus;

/**
 * Structure of PUT /campaigns/{campaignId}/orders/{orderId}/status request
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/put-campaigns-id-orders-id-status.xml">reference</a>
 */
public class Status
{
    OrderStatus status;
    OrderSubstatus substatus;
}
