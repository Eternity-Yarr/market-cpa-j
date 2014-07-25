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

public class Main
{
	private static Logger log = LoggerFactory.getLogger(Main.class);
	final public static DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
	public static GsonWrapper g = GsonWrapper.i();

	public static void main(String[] args)
	{
		//	System.setProperty("javax.net.ssl.trustStore","/home/dvrbuntu/work/market-cpa/src/test/resources/ssl/keystore");
		//	System.setProperty("javax.net.ssl.trustStorePassword","password");
		//	System.setProperty("javax.net.debug","SSL");
		log.info("Started");
		DateTime dt = new DateTime();
		List<Outlet> os = OutletsService.get();
		Ws.run();
	}
}
