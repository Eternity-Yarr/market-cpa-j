package org.lutra.cpa.repository;

import org.joda.time.DateTime;
import org.lutra.cpa.Config;
import org.lutra.cpa.Db;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.cache.OutletsCache;
import org.lutra.cpa.model.Dates;
import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.model.Outlet;
import org.lutra.cpa.service.OutletsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

	public List<DeliveryOption> getAll()
	{
		List<DeliveryOption> options = new ArrayList<>();


		return options;
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
WHERE bsll.name LIKE ? AND bsll.lid = 'ru' AND bsdl.location_type = 'L'
*/
		String q =
			"SELECT \n" +
			"bsdl.delivery_id, bsd.name, \n" +
			"bsd.price, bsd.period_from, \n" +
			"bsd.period_to, bsd.sort \n" +
			"FROM b_sale_location_city_lang AS bsll\n" +
			"LEFT JOIN b_sale_location AS bsl ON bsll.city_id = bsl.city_id\n" +
			"LEFT JOIN b_sale_delivery2location AS bsdl ON bsl.id = bsdl.location_id\n" +
			"LEFT JOIN b_sale_delivery AS bsd ON bsdl.delivery_id = bsd.id\n" +
			"WHERE bsll.name LIKE ? AND bsll.lid = 'ru' AND bsdl.location_type = 'L'";
		Object[] params = {city_name};
		try
		(
			Connection con = Db.ds.getConnection();
			PreparedStatement ps = Helpers.createStatement(con, q, params);
			ResultSet rs = ps.executeQuery()
		)
		{
			while(rs.next())
			{
				DateTime dt = new DateTime();
				DateTime fromDate = dt.plusDays(rs.getInt("period_from"));
				DateTime toDate = dt.plusDays(rs.getInt("period_to"));
				Dates dates = new Dates()
					.setFromDate(fromDate.toDate())
					.setToDate(toDate.toDate());
				List<Outlet> outlets = new ArrayList<>();//OutletsCache.get().get();
				DeliveryOption d = new DeliveryOption();
				d
					.setId(rs.getInt("delivery_id"))
					.setDates(dates)
					.setOutlets(outlets)
					.setPrice(rs.getDouble("price"))
					.setServiceName(rs.getString("name"))
					.setType(Config.deliveryType_mapping.get(rs.getInt("delivery_id")));
				xs.add(d);
			}
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return xs;
	}
}
