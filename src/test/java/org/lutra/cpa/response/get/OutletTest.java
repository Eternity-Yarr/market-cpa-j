package org.lutra.cpa.response.get;

import org.junit.Test;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Main;
import org.lutra.cpa.model.Outlet;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertNotNull;

/**
 * Serialize/deserialize test of GET /campaigns/{campaignId}/outlets/{outletId} response object
 */
public class OutletTest
{
    @Test
    public void testOutletResponseDeserialize() throws Exception
    {
        URL url = OutletTest.class.getResource("outletResponse.json");
        String json = Helpers.readFile(url, StandardCharsets.UTF_8);
        Outlet o = Main.g.fromJson(json, OutletResponse.class).uw();
        assertNotNull(o);
    }
}