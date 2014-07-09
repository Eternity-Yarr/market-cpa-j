package org.lutra.cpa.repository;

import org.joda.time.DateTime;
import org.lutra.cpa.Config;
import org.lutra.cpa.Db;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.model.*;
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
	private static Logger log = LoggerFactory.getLogger("DeliveryRepo");
	public List<DeliveryOption> getAll(DeliveryRequest target, double total)
	{
		List<DeliveryOption> options = new ArrayList<>();
		//1. Pickup
		if(target.getAddress().getCity().equals("Москва"))
			options.addAll(getPickupOption(total));

		//TODO:
		return options;
	}

	private List<DeliveryOption> getPickupOption(double total)
	{
		List<DeliveryOption> ret = new ArrayList<>();
		String q =
			"SELECT id FROM b_iblock_element WHERE IBLOCK_ID = 8";
		Object[] params = {total, 1, 4};
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
				List<Outlet> outlets = OutletsService.get();

				DeliveryOption option = new DeliveryOption();
				if(total > rs.getDouble("order_price_from"))
				{
					option
						.setId(Config.outlets_mapping.get(rs.getInt("id")))
						.setDates(dates)
						.setOutlets(outlets)
						.setPrice(rs.getDouble("price"))
						.setServiceName(rs.getString("name"))
						.setType(DeliveryType.PICKUP)
					;
					ret.add(option);
				}

			}
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return ret;
	}

}
