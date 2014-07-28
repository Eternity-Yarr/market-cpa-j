package org.lutra.cpa.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 28.07.2014 at 17:08
 * SessionsCache of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class SessionsCache
{
	final private static Cache<String, Boolean> cache = CacheBuilder
		.newBuilder()
		.expireAfterAccess(90, TimeUnit.MINUTES)
		.build();

	public static void put(String hash)
	{
		cache.put(hash, true);
	}
	public static boolean contains(String hash)
	{
		return hash != null && cache.getIfPresent(hash) != null;
	}
}