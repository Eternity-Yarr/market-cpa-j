package org.lutra.cpa.service;

import java.net.HttpCookie;

/**
 * 11.06.2014 at 15:32
 * CookieService of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class CookieService
{
	public static HttpCookie setCookie(String key, String value)
	{
		HttpCookie c = new HttpCookie(key, value);
		c.setMaxAge(12 * 30 * 24 * 60 * 60); // About an year of MaxAge
		c.setPath("/");
		c.setVersion(0);

		return c;
	}
	public static HttpCookie delCookie(String key, String value)
	{
		HttpCookie c = new HttpCookie(key, value);
		c.setMaxAge(0); // A zero value causes the cookie to be deleted.
		c.setPath("/");
		c.setVersion(0);

		return c;
	}
}
