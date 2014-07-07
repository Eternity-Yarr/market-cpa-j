package org.lutra.cpa.request.put;

import org.junit.Test;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Main;
import org.lutra.cpa.model.OrderStatus;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Serialize/deserialize test of PUT /campaigns/{campaignId}/orders/{orderId}/status request object
 */
public class StatusTest
{
    @Test
    public void testStatusRequestDeserialize() throws Exception
    {
        URL url = StatusTest.class.getResource("statusRequest.json");
        String json = Helpers.readFile(url, StandardCharsets.UTF_8);
        Status o = Main.g.fromJson(json, StatusRequest.class).uw();
        assertEquals(OrderStatus.DELIVERY, o.status);
        assertNotNull(o);
    }
}