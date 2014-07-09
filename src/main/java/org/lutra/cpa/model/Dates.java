package org.lutra.cpa.model;

import org.lutra.cpa.Main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates
{
	public Date fromDate;
	public Date toDate;

	public Dates setFromDate(Date fromDate)
	{
		this.fromDate = fromDate;

		return this;
	}
	public Dates setToDate(Date toDate)
	{
		this.toDate = toDate;

		return this;
	}
	public String getFromDate()
	{
		return Main.fmt.format(fromDate);
	}
	public String getToDate()
	{
		return Main.fmt.format(toDate);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Dates))
			return false;
		Dates that = (Dates)obj;

		return
					that.fromDate.equals(this.fromDate)
			&& 	that.toDate.equals(this.toDate);
	}
}

