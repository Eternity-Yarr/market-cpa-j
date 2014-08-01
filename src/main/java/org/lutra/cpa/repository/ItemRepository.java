package org.lutra.cpa.repository;

import java.util.List;

/**
 * 01.08.2014 at 19:05
 * ItemRepository of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public interface ItemRepository
{
	/**
	 * @param product_id if of product
	 * @return price of product
	 */
	double getPrice(String product_id);
	/**
	 * Returns list of store ids where this particular product is in stock
	 * @param id product id
	 * @return list of store ids where this particular product is in stock
	 */
	List<Integer> getAvailability(String id);
}
