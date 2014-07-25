package org.lutra.cpa.service;

import org.lutra.cpa.Config;
import org.lutra.cpa.Db;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.cache.OutletsCache;
import org.lutra.cpa.model.Outlet;
import org.lutra.cpa.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 24.07.2014 at 15:06
 * ItemService of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class ItemService
{
	final private static Logger log = LoggerFactory.getLogger(ItemService.class);
	private static ItemService instance;

	public static ItemService i()
	{
		if(instance == null)
			instance = new ItemService();

		return instance;
	}

	public Set<Outlet> getAvailability(String id)
	{
		Set<Outlet> ret = new HashSet<>();
		List<Integer> store_ids = ItemRepository.i().getAvailability(id);

		for(Integer store_id : store_ids)
		{
			Integer outlet_id = Config.outlets_mapping.get(store_id);
			if(outlet_id == null)
				log.info("Outlet with {} store id not found in config", store_id);
			else
				ret.add(OutletsCache.get(Config.outlets_mapping.get(store_id)));
		}

		return  ret;
	}

	public double getPrice(String id)
	{
		return ItemRepository.i().getPrice(id);
	}
}
