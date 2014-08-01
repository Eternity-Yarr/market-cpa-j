package org.lutra.cpa;

import org.joda.time.DateTime;
import org.lutra.cpa.model.Outlet;
import org.lutra.cpa.service.OutletsService;
import org.lutra.cpa.wrapper.GsonWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class Main
{
	final private static Logger log = LoggerFactory.getLogger(Main.class);
	public static DateFormat fmt()
	{
		return new SimpleDateFormat("dd-MM-yyyy");
	}
	final public static GsonWrapper g = GsonWrapper.i();

	public static void main(String[] args)
	{
		TimeZone.setDefault(TimeZone.getTimeZone("MSK"));
		log.info("Started");
		DateTime dt = new DateTime();
		List<Outlet> os = OutletsService.i().get();
		Db.setupDriver();
		Ws.run();
	}
}
