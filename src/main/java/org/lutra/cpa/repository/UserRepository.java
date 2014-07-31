package org.lutra.cpa.repository;

import org.lutra.cpa.Db;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 31.07.2014 at 13:16
 * UserRepository of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class UserRepository
{
	final private static Logger log = LoggerFactory.getLogger(UserRepository.class);
	private static UserRepository instance;

	public static UserRepository i()
	{
		if(instance == null)
			instance = new UserRepository();

		return instance;
	}

	public User get(int user_id)
	{
		User ret = null;
		String q = "SELECT login, name, last_name FROM b_user WHERE id = ?";
		Object[] params = {user_id};
		try
			(
				Connection con = Db.ds.getConnection();
				PreparedStatement ps = Helpers.createStatement(con, q, params);
				ResultSet rs = ps.executeQuery()
			)
		{
			if(rs.next())
			{
				ret = new User();
				ret.id = user_id;
				ret.login = rs.getString("login");
				ret.last_name = rs.getString("last_name");
				ret.name = rs.getString("name");
			}
			else
				log.info("User {} not found", user_id);
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return ret;
	}
}
