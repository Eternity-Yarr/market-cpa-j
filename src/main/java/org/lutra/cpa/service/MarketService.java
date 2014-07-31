package org.lutra.cpa.service;


import org.lutra.cpa.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

public class MarketService
{
	final private static String BaseURL = "https://api.partner.market.yandex.ru/v2";
	final private static Logger log = LoggerFactory.getLogger(MarketService.class);
	private static MarketService instance;

	public static MarketService i()
	{
		if(instance == null)
			instance = new MarketService();

		return instance;
	}

	public String getRequest(String path)
	{
		URL url;
		StringBuilder content = new StringBuilder();
		try
		{
			url = new URL(BaseURL + path);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			String OAuth =
				String.format
					(
						"OAuth oauth_token=\"%s\", oauth_client_id=\"%s\", oauth_login=\"%s\"",
						Config.i().oauth_token, Config.i().oauth_client_id, Config.i().oauth_login
					);
			con.setRequestProperty("Authorization", OAuth);
			con.setRequestMethod("GET");
			con.setDoInput(true);
			BufferedReader br;
			if(con.getResponseCode() != 200)
			{
				log.info("Failed getRequest to {}", path);
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			else
			{
				log.info("Succeed getRequest to {}", path);
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			String input;
			while((input = br.readLine()) != null)
				content.append(input);
			if(con.getResponseCode() != 200)
				log.error(content.toString());
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return content.toString();
	}

	public String putRequest(String path, String body)
	{
		URL url;
		StringBuilder content = new StringBuilder();
		try
		{
			url = new URL(BaseURL + path);
			//url = new URL("https://localhost:10433/loopback?" + path);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			String OAuth =
				String.format
					(
						"OAuth oauth_token=\"%s\", oauth_client_id=\"%s\", oauth_login=\"%s\"",
						Config.i().oauth_token, Config.i().oauth_client_id, Config.i().oauth_login
					);
			con.setRequestProperty("Authorization", OAuth);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Content-Length", "" + body.length());
			con.setRequestMethod("PUT");
			con.setDoOutput(true);
			con.setDoInput(true);
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			out.write(body);
			out.close();
			BufferedReader br;
			if(con.getResponseCode() != 200)
			{
				log.info("Failed getRequest to {}", path);
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			else
			{
				log.info("Succeed getRequest to {}", path);
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			String input;
			while((input = br.readLine()) != null)
				content.append(input);
			if(con.getResponseCode() != 200)
				log.error(content.toString());
		}
		catch(Exception e)
		{
			log.error(e.toString(), e);
		}

		return content.toString();
	}
}
