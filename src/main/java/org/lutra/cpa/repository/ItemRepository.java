package org.lutra.cpa.repository;

import org.lutra.cpa.Db;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.model.Outlet;
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

	public List<Integer> getAvailability(String id)
	{
		List<Integer> ret = new ArrayList<>();
/*
SELECT price FROM b_catalog_price WHERE id = ?
*/
		String q = "";
		Object[] params = {id};
		try
			(
				Connection con = Db.ds.getConnection();
				PreparedStatement ps = Helpers.createStatement(con, q, params);
				ResultSet rs = ps.executeQuery()
			)
		{
			if(rs.next())
				ret.add(rs.getInt(""));
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return  ret;
	}
}
