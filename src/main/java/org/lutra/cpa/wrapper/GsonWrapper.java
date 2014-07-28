package org.lutra.cpa.wrapper;

import com.google.gson.*;
import org.lutra.cpa.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

/**
 * 25.07.2014 at 18:04
 * GsonWrapper of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class GsonWrapper
{
	private static Logger log = LoggerFactory.getLogger("G:");
	private static Gson gson = setUpGson();
	private static GsonWrapper instance;

	GsonWrapper()
	{
		gson = setUpGson();
	}

	public static GsonWrapper i()
	{
		if(instance == null)
			instance = new GsonWrapper();

		return instance;
	}

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
						return new JsonPrimitive(Main.fmt.format(src));
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
							d = json == null ? null : Main.fmt.parse(json.getAsString());
						}
						catch(ParseException e)	{ /*don't care*/ }
						return d;
					}
				}
			);

		return gb.create();
	}

	public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException
	{
		log.info("<{}> f: {}", classOfT, json);

		return gson.fromJson(json, classOfT);
	}

	public String toJson(Object src)
	{
		String ret = gson.toJson(src);
		log.info("<{}> t: {}", src.getClass(), ret);

		return  ret;
	}

}
