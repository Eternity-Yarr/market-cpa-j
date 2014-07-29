package org.lutra.cpa;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyVetoException;

public class Db
{
	final private static Logger log = LoggerFactory.getLogger(Db.class);
	public static ComboPooledDataSource ds;

	public static void setupDriver()
	{
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		try
		{
			cpds.setDriverClass("com.mysql.jdbc.Driver");
		}
		catch(PropertyVetoException e)
		{
			log.error(e.toString(), e);
		}
		cpds.setJdbcUrl(Config.i().DB_uri);
		cpds.setUser(Config.i().DB_user);
		cpds.setPassword(Config.i().DB_pass);

		ds = cpds; //FIXME: lol
	}
}
