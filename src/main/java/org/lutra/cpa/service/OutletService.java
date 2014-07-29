package org.lutra.cpa.service;

import org.lutra.cpa.Config;
import org.lutra.cpa.Main;
import org.lutra.cpa.response.get.OutletResponse;

public class OutletService
{
	private static OutletService instance;
	public static OutletService i()
	{
		if(instance == null)
			instance = new OutletService();

		return instance;
	}

	public OutletResponse get(int id)
	{
		assert (id != 0); //TODO: Meh..
		String path = String.format("/campaigns/%s/outlets/%s.json", Config.i().campaignId, id);
		String json = MarketService.i().getRequest(path);

		return Main.g.fromJson(json, OutletResponse.class);
	}
}
