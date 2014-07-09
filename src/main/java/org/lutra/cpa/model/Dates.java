package org.lutra.cpa.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates
{

	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

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
		return df.format(fromDate);
	}
	public String getToDate()
	{
		return df.format(toDate);
	}
}
