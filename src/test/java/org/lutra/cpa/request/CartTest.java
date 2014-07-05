package org.lutra.cpa.request;

import com.google.gson.Gson;
import org.junit.Test;
import org.lutra.cpa.Helpers;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

/**
 * Serialize/deserialize test of POST /cart request object
 */
public class CartTest
{
    @Test
    public void testCartRequestDeserialize() throws Exception
    {
        URL url = CartTest.class.getResource("cartRequest.json");
        String json = Helpers.readFile(url, StandardCharsets.UTF_8);
        Cart c = new Gson().fromJson(json, CartRequest.class).uw();
        assertNotNull(c);
    }
}