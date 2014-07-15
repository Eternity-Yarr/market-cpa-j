package org.lutra.ems.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 15.07.2014 at 12:23
 * Request of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class Request
{
	final private static Logger log = LoggerFactory.getLogger(Request.class);
	public static String asJSON(String path) throws Exception
	{
		log.info("Requesting {}", path);
		URL url = new URL(path);
		URLConnection con = (URLConnection)url.openConnection();
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		while((line = br.readLine()) != null)
			sb.append(line);
		br.close();
		log.info("Got response {}", sb.toString());

		return sb.toString();
	}
}
