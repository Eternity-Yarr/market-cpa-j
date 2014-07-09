package org.lutra.cpa;

import com.google.gson.*;
import org.joda.time.DateTime;
import org.lutra.cpa.model.Outlet;
import org.lutra.cpa.service.OutletsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main
{
	private static Logger log = LoggerFactory.getLogger("Main");
	public static Gson g = setUpGson();
	final public static DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
	public static Gson setUpGson()
	{
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter
			(
				Date.class, new JsonSerializer<Date>()
				{
					@Override
					public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context)
					{
						return new JsonPrimitive(fmt.format(src));
					}
				}
			);
		gb.registerTypeAdapter
			(
				Date.class, new JsonDeserializer<Date>()
				{
					@Override
					public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
					{
						Date d = null;
						try
						{
							d = json == null ? null : fmt.parse(json.getAsString());
						}
						catch(ParseException e)
						{
						}
						return d;
					}
				}
			);
		return gb.create();
	}

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
