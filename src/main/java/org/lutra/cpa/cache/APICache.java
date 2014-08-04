package org.lutra.cpa.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.lutra.cpa.service.MarketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 31.07.2014 at 14:49
 * APICache of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class APICache
{
	final private static Logger log = LoggerFactory.getLogger(APICache.class);
	final private static Cache<String, String> cache = CacheBuilder
		.newBuilder()
		.expireAfterWrite(1, TimeUnit.MINUTES)
		.build();

	public static void invalidate()
	{
		cache.invalidateAll();
	}

	public static String get(final String req)
	{
		String ret = "";
		try
		{
			ret = cache.get(req, new Callable<String>()
			{
				@Override
				public String call() throws Exception
				{
					return MarketService.i().getRequestUncached(req);
				}
			});
		}
		catch(Exception e)
		{
				log.error(e.toString(), e);
		}

		return ret;
	}
}
