package org.lutra.cpa.service;

import org.junit.Test;

public class MarketTest
{
    @Test
    public void testConnection()
    {
        String reply = Market.getRequest("/campaigns.json");
        System.out.println(reply);
    }
}