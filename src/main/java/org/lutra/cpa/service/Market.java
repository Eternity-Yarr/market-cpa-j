package org.lutra.cpa.service;


import org.lutra.cpa.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Market
{
    final static String BaseURL = "https://api.partner.market.yandex.ru/v2";
		final static Logger log = LoggerFactory.getLogger(Market.class);

    public static String request(String path)
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
                    Config.oauth_token, Config.oauth_client_id, Config.oauth_login
                )
            ;
            con.setRequestProperty("Authorization",OAuth);
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            BufferedReader br;
            if(con.getResponseCode() != 200)
						{
							log.info("Failed request to {}", path);
							br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
						}
            else
						{
							log.info("Succeed request to {}", path);
							br = new BufferedReader(new InputStreamReader(con.getInputStream()));
						}
            String input;
            while((input = br.readLine()) != null)
                content.append(input);
        }
        catch(Exception e)
        {
					log.error(e.toString(), e);
        }

        return content.toString();
    }
}
