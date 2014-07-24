package org.lutra.cpa.service;

import org.lutra.cpa.Db;
import org.lutra.cpa.Helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
