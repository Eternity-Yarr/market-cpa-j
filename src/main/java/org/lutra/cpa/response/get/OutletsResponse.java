package org.lutra.cpa.response.get;

import org.lutra.cpa.model.Outlet;
import org.lutra.cpa.model.Pager;

import java.util.List;

/**
 * Structure of GET /campaigns/{campaignId}/outlets response
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/get-campaigns-id-outlets.xml">reference</a>
 */
public class OutletsResponse
{
    Pager pager;
    List<Outlet> outlets;

    public List<Outlet> getOutlets()
    {
        return outlets;
    }
}
