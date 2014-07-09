package org.lutra.cpa.service;

import org.lutra.cpa.Config;
import org.lutra.cpa.Main;
import org.lutra.cpa.response.get.OutletResponse;

public class OutletService
{
	public static OutletResponse get(int id)
	{
		assert (id != 0); //TODO: Meh..
		String path = String.format("/campaigns/%s/outlets/%s.json", Config.campaignId, id);
		String json = Market.getRequest(path);

		return Main.g.fromJson(json, OutletResponse.class);
	}
}
