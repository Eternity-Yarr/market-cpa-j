package org.lutra.cpa.response.get;

import org.lutra.cpa.model.Outlet;

/**
 * Structure of GET /campaigns/{campaignId}/outlet/{outletId} response
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/get-campaigns-id-outlets-id.xml">reference</a>
 */
public class OutletResponse
{
    Outlet outlet;
    public Outlet uw(){return outlet;}

}
