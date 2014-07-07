package org.lutra.cpa.response.get;

import org.junit.Test;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Main;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertNotNull;

/**
 * Serialize/deserialize test of GET /campaigns/{campaignId}/orders response object
 */
public class OrdersTest
{
    @Test
    public void testOrdersResponseDeserialize() throws Exception
    {
        URL url = OrdersTest.class.getResource("ordersResponse.json");
        String json = Helpers.readFile(url, StandardCharsets.UTF_8);
        OrdersResponse os = Main.g.fromJson(json, OrdersResponse.class);
        assertNotNull(os);
    }
}