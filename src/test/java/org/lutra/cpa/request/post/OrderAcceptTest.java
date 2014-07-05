package org.lutra.cpa.request.post;

import com.google.gson.Gson;
import org.junit.Test;
import org.lutra.cpa.Helpers;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertNotNull;

/**
 * Serialize/deserialize test of POST /order/accept request object
 */
public class OrderAcceptTest
{
    @Test
    public void testOrderAcceptRequestDeserialize() throws Exception
    {
        URL url = OrderAcceptTest.class.getResource("orderAcceptRequest.json");
        String json = Helpers.readFile(url, StandardCharsets.UTF_8);
        OrderAccept o = new Gson().fromJson(json, OrderAcceptRequest.class).uw();
        assertNotNull(o);
    }
}