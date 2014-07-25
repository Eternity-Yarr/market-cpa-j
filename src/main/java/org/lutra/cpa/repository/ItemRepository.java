package org.lutra.cpa.repository;

import org.lutra.cpa.Db;
import org.lutra.cpa.Helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 25.07.2014 at 10:47
 * ItemRepository of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class ItemRepository
{
	final private static Logger log = LoggerFactory.getLogger(ItemRepository.class);
	private static ItemRepository instance;

	public static ItemRepository i()
	{
		if(instance==null)
			instance = new ItemRepository();

		return instance;
	}

	public double getPrice(String id)
	{
		double ret = 0;
/*
SELECT price FROM b_catalog_price WHERE id = ?
*/
		String q = "SELECT price FROM b_catalog_price WHERE product_id = ?";
		Object[] params = {id};
		try
			(
				Connection con = Db.ds.getConnection();
				PreparedStatement ps = Helpers.createStatement(con, q, params);
				ResultSet rs = ps.executeQuery()
			)
		{
			if(rs.next())
				ret = rs.getDouble("price");
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return  ret;
	}

	/**
	 * @param id item id to check availability
	 * @return list of store ids with non-zero quantity of items available
	 */
	public List<Integer> getAvailability(String id)
	{
		List<Integer> ret = new ArrayList<>();
/*
SELECT store_id FROM my_availability WHERE item_id = ? AND aviable > 0
*/
		String q = "SELECT store_id FROM my_availability WHERE item_id = ? AND aviable > 0"; // Yeah, i know.
		Object[] params = {id};
		try
			(
				Connection con = Db.ds.getConnection();
				PreparedStatement ps = Helpers.createStatement(con, q, params);
				ResultSet rs = ps.executeQuery()
			)
		{
			if(rs.next())
				ret.add(rs.getInt("store_id"));
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return  ret;
	}
}
