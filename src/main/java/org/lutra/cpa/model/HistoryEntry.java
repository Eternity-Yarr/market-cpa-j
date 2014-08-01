package org.lutra.cpa.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 29.07.2014 at 14:45
 * HistoryEntry of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class HistoryEntry
{
	public Date date_added;
	public User user;
	public String message;

	private static DateFormat fmt()
	{
		return new SimpleDateFormat("HH:mm:ss dd/MM/yy");
	}

	public String getTextDate()
	{
		return fmt().format(date_added);
	}

}
