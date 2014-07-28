package org.lutra.cpa;

import org.lutra.cpa.model.DeliveryType;

import java.util.HashMap;
import java.util.Properties;

public class Config
{
	final public String oauth_token;
	final public String oauth_client_id;
	final public String oauth_login;
	final public int campaignId;
	final public String DB_uri;
	final public String DB_user;
	final public String DB_pass;
	final public boolean disable_ssl;
	final public int listening_port;
	final public static HashMap<Integer, Integer> outlets_mapping = new HashMap<>();
	static
	{
		outlets_mapping.put(107, 272866);
		outlets_mapping.put(112, 268441);
		outlets_mapping.put(110, 246787);
		outlets_mapping.put(862, 87363);
		outlets_mapping.put(12194, 347392);
	}
	final public static HashMap<Integer, DeliveryType> deliveryType_mapping = new HashMap<>();
	static
	{
		deliveryType_mapping.put(1, DeliveryType.PICKUP);
		deliveryType_mapping.put(2, DeliveryType.POST);
		deliveryType_mapping.put(3, DeliveryType.DELIVERY);
		deliveryType_mapping.put(4, DeliveryType.DELIVERY);
		deliveryType_mapping.put(11, DeliveryType.DELIVERY);
	}
	public static Config instance;
	public static Config i()
	{
		if(instance == null)
			instance = new Config();

		return instance;
	}

	private Config()
	{
		Properties p = new Properties();
		try
		{
			p.load(Config.class.getResourceAsStream("config.properties"));
		}
		catch(Exception e){	}
		oauth_token = p.getProperty("oauth_token");
		oauth_client_id = p.getProperty("oauth_client_id");
		oauth_login = p.getProperty("oauth_login");
		campaignId = Integer.parseInt(p.getProperty("campaignId"));
		DB_uri = p.getProperty("DB_uri");
		DB_user = p.getProperty("DB_user");
		DB_pass = p.getProperty("DB_pass");
		disable_ssl = Boolean.valueOf(p.getProperty("disable_ssl"));
		listening_port = Integer.parseInt(p.getProperty("listening_port"));
	}
}
