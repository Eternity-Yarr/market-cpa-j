package org.lutra.cpa.request.post;

import org.junit.Test;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Main;

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
        OrderAccept o = Main.g.fromJson(json, OrderAcceptRequest.class).uw();
        assertNotNull(o.currency);
    }
}