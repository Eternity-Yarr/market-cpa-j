package org.lutra.cpa.response.get;

import com.google.gson.Gson;
import org.junit.Test;
import org.lutra.cpa.Helpers;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertNotNull;

/**
 * Serialize/deserialize test of GET /campaigns/{campaignId}/outlets response object
 */
public class OutletsTest
{
    @Test
    public void testOutletsResponseDeserialize() throws Exception
    {
        URL url = OutletsTest.class.getResource("outletsResponse.json");
        String json = Helpers.readFile(url, StandardCharsets.UTF_8);
        OutletsResponse os = new Gson().fromJson(json, OutletsResponse.class);
        assertNotNull(os);
    }
}