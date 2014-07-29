package org.lutra.cpa.service;

import org.lutra.cpa.Db;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.cache.SessionsCache;
import org.lutra.cpa.model.HistoryEntry;
import org.lutra.cpa.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
	private static HistoryService instance;

	public static HistoryService i()
	{
		if(instance == null)
			instance = new HistoryService();

		return instance;
	}

	public void add(String hash, int order_id, String message)
	{
		User u = UserService.i().get(SessionsCache.get(hash));
		if(u != null)
			add(u, order_id, message);
		else
			log.info("Hash {} is obsolete", hash);
	}

	public void add(User user, int order_id, String message)
	{
		String q = "INSERT INTO market_cpa_log (user_id, order_id, message) VALUES (?,?,?)";
		Object[] params = {user.id, order_id, message};
		try
		(
			Connection con = Db.ds.getConnection();
			PreparedStatement ps = Helpers.createStatement(con, q, params)
		)
		{
			ps.execute();
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}
	}

	public List<HistoryEntry> get(int order_id)
	{
		List<HistoryEntry> ret = new ArrayList<>();
		String q = "SELECT user_id, message, date_added FROM market_cpa_log WHERE order_id = ? ORDER BY date_added ASC";
		Object[] params = {order_id};
		try
		(
			Connection con = Db.ds.getConnection();
			PreparedStatement ps = Helpers.createStatement(con, q, params);
			ResultSet rs = ps.executeQuery()
		)
		{
			while(rs.next())
			{
				HistoryEntry he = new HistoryEntry();
				he.date_added = new Date(rs.getTimestamp("date_added").getTime());
				he.message = rs.getString("message");
				he.user = UserService.i().get(rs.getInt("user_id"));
				ret.add(he);
			}
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return ret;
	}
}
