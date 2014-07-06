package org.lutra.cpa.response;

import com.google.gson.Gson;
import org.junit.Test;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Main;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertNotNull;

/**
 * Serialize/deserialize test of POST /cart request object
 */
public class CartTest
{
    @Test
    public void testCartResponseDeserialize() throws Exception
    {
        URL url = CartTest.class.getResource("cartResponse.json");
        String json = Helpers.readFile(url, StandardCharsets.UTF_8);
        Cart c = Main.g.fromJson(json, CartResponse.class).uw();
        assertNotNull(c);
    }
}