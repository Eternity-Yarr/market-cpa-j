package org.lutra.cpa.service;

import org.lutra.cpa.cache.SessionsCache;
import org.lutra.cpa.model.HistoryEntry;
import org.lutra.cpa.model.User;
import org.lutra.cpa.repository.HistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 29.07.2014 at 14:38
 * HistoryService of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class HistoryService
{
	final private static Logger log = LoggerFactory.getLogger(HistoryService.class);
	final private static HistoryRepository hr = HistoryRepository.i();
	private static HistoryService instance;

	public static HistoryService i()
	{
		if(instance == null)
			instance = new HistoryService();

		return instance;
	}

	public void add(String hash, int order_id, String message)
	{
		User u = UserService.i().get(SessionsCache.get(hash)); //TODO: meh?
		if(u != null)
			add(u, order_id, message);
		else
			log.info("Hash {} is obsolete", hash);
	}

	public void add(User user, int order_id, String message)
	{
		hr.add(user.id, order_id, message);
	}

	public List<HistoryEntry> get(int order_id)
	{
		return hr.get(order_id);
	}
	public List<HistoryEntry> getRecent()
	{
		return hr.getRecent();
	}
}
