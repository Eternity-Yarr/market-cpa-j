package org.lutra.cpa.request.post;

import com.google.gson.Gson;
import org.junit.Test;
import org.lutra.cpa.Helpers;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertNotNull;

/**
 * Serialize/deserialize test of POST /order/status request object
 */
public class OrderStatusTest
{
    @Test
    public void testOrderAcceptRequestDeserialize() throws Exception
    {
        URL url = OrderStatusTest.class.getResource("orderStatusRequest.json");
        String json = Helpers.readFile(url, StandardCharsets.UTF_8);
        OrderStatus o = new Gson().fromJson(json, OrderStatusRequest.class).uw();
        assertNotNull(o);
    }
}