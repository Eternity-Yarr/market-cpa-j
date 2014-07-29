package org.lutra.cpa.service;

import org.lutra.cpa.Config;
import org.lutra.cpa.Main;
import org.lutra.cpa.response.get.OrderResponse;

public class OrderService
{
	private static OrderService instance;

	public static OrderService i()
	{
		if(instance == null)
			instance = new OrderService();

		return instance;
	}

	public static OrderResponse get(int id)
	{
		assert (id != 0); //TODO: Meh..
		String path = String.format("/campaigns/%s/orders/%s.json", Config.i().campaignId, id);
		String json = MarketService.i().getRequest(path);

		return Main.g.fromJson(json, OrderResponse.class);
	}
}
