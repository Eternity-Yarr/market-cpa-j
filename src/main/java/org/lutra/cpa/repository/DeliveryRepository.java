package org.lutra.cpa.repository;

import org.joda.time.DateTime;
import org.lutra.cpa.Config;
import org.lutra.cpa.Db;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.model.Dates;
import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.model.DeliveryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeliveryRepository
{
	final private static Logger log = LoggerFactory.getLogger("DeliveryRepo");
	private static DeliveryRepository instance;

	public static DeliveryRepository i()
	{
		if(instance==null)
			instance = new DeliveryRepository();

		return instance;
	}

	private List<DeliveryOption> formList(ResultSet rs) throws SQLException
	{
		List<DeliveryOption> xs = new ArrayList<>();

		while(rs.next())
		{
			DateTime dt = new DateTime();
			DateTime fromDate = dt.plusDays(rs.getInt("period_from"));
			DateTime toDate = dt.plusDays(rs.getInt("period_to"));
			Dates dates = new Dates()
				.setFromDate(fromDate.toDate())
				.setToDate(toDate.toDate());
			Set<Integer> outlets = new HashSet<>();
			DeliveryOption d = new DeliveryOption();
			d
				.setId(rs.getInt("delivery_id"))
				.setDates(dates)
				.setOutlets(Config.deliveryType_mapping.get(rs.getInt("delivery_id")).equals(DeliveryType.PICKUP) ? outlets : null)
				.setPrice(rs.getDouble("price"))
				.setServiceName(rs.getString("name"))
				.setType(Config.deliveryType_mapping.get(rs.getInt("delivery_id")));
			xs.add(d);
		}
		return xs;
	}
	public List<DeliveryOption> getAll()
	{
		List<DeliveryOption> xs = new ArrayList<>();
/*
SELECT
bsdl.delivery_id, bsd.name,
bsd.price, bsd.period_from,
bsd.period_to, bsd.sort
FROM b_sale_location_city_lang AS bsll
LEFT JOIN b_sale_location AS bsl ON bsll.city_id = bsl.city_id
LEFT JOIN b_sale_delivery2location AS bsdl ON bsl.id = bsdl.location_id
LEFT JOIN b_sale_delivery AS bsd ON bsdl.delivery_id = bsd.id
WHERE bsll.lid = 'ru' AND bsdl.location_type = 'L' AND bsd.active = 'Y'
GROUP BY bsd.name
*/
		String q =
			"SELECT\n" +
			"bsdl.delivery_id, bsd.name,\n" +
			"bsd.price, bsd.period_from,\n" +
			"bsd.period_to, bsd.sort\n" +
			"FROM b_sale_location_city_lang AS bsll\n" +
			"LEFT JOIN b_sale_location AS bsl ON bsll.city_id = bsl.city_id\n" +
			"LEFT JOIN b_sale_delivery2location AS bsdl ON bsl.id = bsdl.location_id\n" +
			"LEFT JOIN b_sale_delivery AS bsd ON bsdl.delivery_id = bsd.id\n" +
			"WHERE bsll.lid = 'ru' AND bsdl.location_type = 'L' AND bsd.active = 'Y'\n" +
			"GROUP BY bsd.name";
		try
			(
				Connection con = Db.ds.getConnection();
				PreparedStatement ps = con.prepareStatement(q);
				ResultSet rs = ps.executeQuery()
			)
		{
			xs.addAll(formList(rs));
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return xs;
	}

	public List<DeliveryOption> getByCityName(String city_name)
	{
		List<DeliveryOption> xs = new ArrayList<>();
/*
SELECT
bsdl.delivery_id, bsd.name,
bsd.price, bsd.period_from,
bsd.period_to, bsd.sort
FROM b_sale_location_city_lang AS bsll
LEFT JOIN b_sale_location AS bsl ON bsll.city_id = bsl.city_id
LEFT JOIN b_sale_delivery2location AS bsdl ON bsl.id = bsdl.location_id
LEFT JOIN b_sale_delivery AS bsd ON bsdl.delivery_id = bsd.id
WHERE bsll.name LIKE ? AND bsll.lid = 'ru' AND bsdl.location_type = 'L' AND bsd.active = 'Y'
*/
		String q =
			"SELECT\n" +
			"bsdl.delivery_id, bsd.name,\n" +
			"bsd.price, bsd.period_from,\n" +
			"bsd.period_to, bsd.sort\n" +
			"FROM b_sale_location_city_lang AS bsll\n" +
			"LEFT JOIN b_sale_location AS bsl ON bsll.city_id = bsl.city_id\n" +
			"LEFT JOIN b_sale_delivery2location AS bsdl ON bsl.id = bsdl.location_id\n" +
			"LEFT JOIN b_sale_delivery AS bsd ON bsdl.delivery_id = bsd.id\n" +
			"WHERE bsll.name LIKE ? AND bsll.lid = 'ru' AND bsdl.location_type = 'L' AND bsd.active = 'Y'";
		Object[] params = {city_name};
		try
			(
				Connection con = Db.ds.getConnection();
				PreparedStatement ps = Helpers.createStatement(con, q, params);
				ResultSet rs = ps.executeQuery()
			)
		{
			xs.addAll(formList(rs));
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return xs;
	}

	public List<DeliveryOption> getByTotalAmount(double total)
	{
		List<DeliveryOption> xs = new ArrayList<>();
/*
SELECT
bsdl.delivery_id, bsd.name,
bsd.price, bsd.period_from,
bsd.period_to, bsd.sort
FROM b_sale_location_city_lang AS bsll
LEFT JOIN b_sale_location AS bsl ON bsll.city_id = bsl.city_id
LEFT JOIN b_sale_delivery2location AS bsdl ON bsl.id = bsdl.location_id
LEFT JOIN b_sale_delivery AS bsd ON bsdl.delivery_id = bsd.id
WHERE bsd.order_price_from <= ? AND (bsd.order_price_to > ? OR bsd.order_price_to = 0) AND bsll.lid = 'ru' AND bsdl.location_type = 'L' AND active = 'Y'
GROUP BY bsd.name
*/
		String q =
			"SELECT\n" +
			"bsdl.delivery_id, bsd.name,\n" +
			"bsd.price, bsd.period_from,\n" +
			"bsd.period_to, bsd.sort\n" +
			"FROM b_sale_location_city_lang AS bsll\n" +
			"LEFT JOIN b_sale_location AS bsl ON bsll.city_id = bsl.city_id\n" +
			"LEFT JOIN b_sale_delivery2location AS bsdl ON bsl.id = bsdl.location_id\n" +
			"LEFT JOIN b_sale_delivery AS bsd ON bsdl.delivery_id = bsd.id\n" +
			"WHERE bsd.order_price_from <= ? AND (bsd.order_price_to > ? OR bsd.order_price_to = 0) AND bsll.lid = 'ru' AND bsdl.location_type = 'L' AND active = 'Y' \n" +
			"GROUP BY bsd.name";
		Object[] params = {total, total};
		try
		(
			Connection con = Db.ds.getConnection();
			PreparedStatement ps = Helpers.createStatement(con, q, params);
			ResultSet rs = ps.executeQuery()
		)
		{
			xs.addAll(formList(rs));
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return xs;
	}
}
