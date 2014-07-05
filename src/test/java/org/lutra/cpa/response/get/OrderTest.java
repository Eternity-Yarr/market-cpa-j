package org.lutra.cpa.response.get;

import com.google.gson.Gson;
import org.junit.Test;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.response.Cart;
import org.lutra.cpa.response.CartResponse;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertNotNull;

/**
 * Serialize/deserialize test of GET /campaigns/{campaignId}/orders/{orderId} response object
 */
public class OrderTest
{
    @Test
    public void testOrderResponseDeserialize() throws Exception
    {
        URL url = OrderTest.class.getResource("orderResponse.json");
        String json = Helpers.readFile(url, StandardCharsets.UTF_8);
        Order o = new Gson().fromJson(json, OrderResponse.class).uw();
        assertNotNull(o);
    }
}