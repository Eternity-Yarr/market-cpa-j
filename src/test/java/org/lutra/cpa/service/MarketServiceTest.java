package org.lutra.cpa.service;

import org.junit.Test;

public class MarketServiceTest
{
    @Test
    public void testConnection()
    {
        String reply = MarketService.i().getRequest("/campaigns.json");
        System.out.println(reply);
    }
}