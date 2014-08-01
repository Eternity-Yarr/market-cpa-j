package org.lutra.cpa.repository;

import org.lutra.cpa.model.HistoryEntry;

import java.util.List;

/**
 * 01.08.2014 at 19:04
 * HistoryRepository of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public interface HistoryRepository
{
	/**
	 * Add history entry
	 * @param user_id id of user
	 * @param order_id id of order
	 * @param message message describing action
	 */
	void add(int user_id, int order_id, String message);
	/**
	 * Returns list of history entries for given order id
	 * @param order_id id of order
	 * @return list of entries for this order
	 */
	List<HistoryEntry> get(int order_id);
	/**
	 * @return list of with some fixed amount of recent history entries
	 */
	List<HistoryEntry> getRecent();
}
