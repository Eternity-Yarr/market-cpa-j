package org.lutra.ems;

import com.google.gson.Gson;
import org.lutra.ems.http.Request;
import org.lutra.ems.response.Response;
import org.lutra.ems.response.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 15.07.2014 at 11:49
 * EMSService of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class EMSService
{
	final private static Logger log = LoggerFactory.getLogger(EMSService.class);
	final private List<Location> locations;
	final private double maxWeight;

	public EMSService()
	{
		locations = getLocations();
		maxWeight = getMaxWeight();
		log.info("EMS initialized, {} available locations, {} max weight", locations.size(), maxWeight);
	}
	private double getMaxWeight()
	{
		double ret = 0;
		Response rsp = null;
		try
		{
			rsp = new Gson().fromJson(Request.asJSON("http://emspost.ru/api/rest/?method=ems.get.max.weight"), ResponseWrapper.class).uw();
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}
		if(rsp != null && rsp.stat.equals("ok"))
			try
			{
				ret = Double.parseDouble(rsp.max_weight);
			}
			catch(NumberFormatException e)
			{
				log.error(e.toString(), e);
			}

		return ret;
	}

	public int getPrice(Location from, Location to, double weight)
	{
		int price = 0;
		String url = String.format("http://emspost.ru/api/rest?method=ems.calculate&from=%s&to=%s&weight=%0.2f&type=att", from.value, to.value, weight);
		Response rsp = null;
		try
		{
			rsp = new Gson().fromJson(Request.asJSON(url), ResponseWrapper.class).uw();
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}
		if(rsp != null && rsp.stat.equals("ok"))
			try
			{
					price = Integer.parseInt(rsp.price);
			}
			catch(NumberFormatException e)
			{
				log.error(e.toString(), e);
			}

		return price;
	}

	private List<Location> getLocations()
	{
		List<Location> xs = new ArrayList<>();
		Response rsp = null;
		try
		{
			rsp = new Gson().fromJson(Request.asJSON("http://emspost.ru/api/rest/?method=ems.get.locations&type=russia&plain=true"), ResponseWrapper.class).uw();
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}
		if(rsp != null && rsp.stat.equals("ok"))
			for(Location l : rsp.locations)
				xs.add(l);

		return xs;
	}

	public boolean testConnection()
	{
		Response rsp = null;
		try
		{
			rsp = new Gson().fromJson(Request.asJSON("http://emspost.ru/api/rest/?method=ems.test.echo"), ResponseWrapper.class).uw();
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return rsp != null && rsp.stat.equals("ok");
	}
}
