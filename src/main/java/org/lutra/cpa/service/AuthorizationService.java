package org.lutra.cpa.service;

import org.lutra.cpa.cache.SessionsCache;
import org.lutra.cpa.repository.AuthorizationRepository;
import org.lutra.cpa.repository.BitrixAuthorizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 28.07.2014 at 17:35
 * AuthorizationService of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class AuthorizationService
{
	final private static Logger log = LoggerFactory.getLogger(AuthorizationService.class);
	final private static AuthorizationRepository ar = BitrixAuthorizationRepository.i();
	private static AuthorizationService instance;

	public static AuthorizationService i()
	{
		if(instance == null)
			instance = new AuthorizationService();

		return instance;
	}

	public static int authorized(String token)
	{
		return SessionsCache.get(token);
	}

	public static int authorized(String email, String password)
	{
		return ar.authorized(email, password);
	}
}
