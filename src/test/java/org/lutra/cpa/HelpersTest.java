package org.lutra.cpa;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class HelpersTest
{

	public static class TestClass
	{
		public String a;
		public String b;
		public int c;
	}
	@Test
	public void testdeserializeMap() throws  Exception
	{

		Map<String,String> testMap = new HashMap<>();
		testMap.put("a", "blah");
		testMap.put("c", "123");
		testMap.put("b", "sdfdsfd");

		TestClass ts = Helpers.castMap(testMap, TestClass.class);
		System.out.println(ts);
	}


	@Test
	public void testCreateStatement() throws Exception
	{
		Db.setupDriver();
		String q =
						"SELECT id, name, description, price, order_price_from, order_price_to FROM b_sale_delivery WHERE active = 'Y' AND order_price_from < ? AND id IN (?,?)";
		Object[] params = {500.0, 1, 4};
		try
		(
				Connection con = Db.ds.getConnection();
				PreparedStatement ps = Helpers.createStatement(con, q, params);
				ResultSet rs = ps.executeQuery()
		)
		{
				while(rs.next())
				{
						System.out.println(rs.getString("name"));
				}
		}
	}
}