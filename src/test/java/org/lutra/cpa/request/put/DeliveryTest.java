package org.lutra.cpa.request.put;

import com.google.gson.Gson;
import org.junit.Test;
import org.lutra.cpa.Helpers;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertNotNull;

/**
 * Serialize/deserialize test of PUT /campaigns/{campaignId}/orders/{orderId}/delivery request object
 */
public class DeliveryTest
{
    @Test
    public void testStatusRequestDeserialize() throws Exception
    {
        URL url = StatusTest.class.getResource("deliveryRequest.json");
        String json = Helpers.readFile(url, StandardCharsets.UTF_8);
        DeliveryRequest dr = new Gson().fromJson(json, DeliveryRequest.class);
        assertNotNull(dr);
    }
}
