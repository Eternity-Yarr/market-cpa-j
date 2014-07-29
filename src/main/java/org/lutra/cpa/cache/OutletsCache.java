package org.lutra.cpa.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.lutra.cpa.model.Outlet;
import org.lutra.cpa.response.get.OutletResponse;
import org.lutra.cpa.service.OutletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class OutletsCache
{
	final private static Logger log = LoggerFactory.getLogger(OutletsCache.class);
	final private static Cache<Integer, Outlet> cache = CacheBuilder
		.newBuilder()
		.expireAfterWrite(60, TimeUnit.MINUTES)
		.build();
	public static void put(Outlet o)
	{
		assert (o != null);
		assert (o.getId() != 0); //TODO: Meh..

		cache.put(o.getId(), o);
	}

	public static Outlet get(final int id)
	{
		Outlet o = null;
		try
		{
			o = cache.get
				(
					id, new Callable<Outlet>()
					{
						@Override
						public Outlet call() throws Exception
						{
							log.info("Querying API for {}", id);
							OutletResponse o = OutletService.get(id);
							if(o != null)
								return o.uw();
							else
								return null; //TODO: Meh..
						}
					}
				);
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return o;
	}
}
