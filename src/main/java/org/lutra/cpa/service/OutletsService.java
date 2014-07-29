package org.lutra.cpa.service;

import org.lutra.cpa.Config;
import org.lutra.cpa.Main;
import org.lutra.cpa.cache.OutletsCache;
import org.lutra.cpa.model.Outlet;
import org.lutra.cpa.response.get.OutletsResponse;

import java.util.ArrayList;
import java.util.List;

public class OutletsService
{
	private static OutletsService instance;

	public static OutletsService i()
	{
		if(instance == null)
			instance = new OutletsService();

		return instance;
	}

	public List<Outlet> get()
	{
		List<Outlet> ret = new ArrayList<>();
		int ps = 50;
		int p = 0;
		String path = String.format("/campaigns/%s/outlets.json?pageSize=%s&page=%s", Config.i().campaignId, ps, p);
		String json = MarketService.i().getRequest(path);
		OutletsResponse or = Main.g.fromJson(json, OutletsResponse.class);

		if(or != null)
		{
			List<Outlet> os = or.getOutlets();
			for(Outlet o : os)
			{
				ret.add(o);
				OutletsCache.put(o);
			}
		}

		return ret;
	}
}
