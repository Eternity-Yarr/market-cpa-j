package org.lutra.cpa.model;

import java.util.LinkedHashSet;
import java.util.List;

public class DeliveryOption
{
	int id;
	DeliveryType type;
	String serviceName;
	double price;
	Dates dates;
	int sort;
	List<Outlet> outlets;

	public int getId()
	{
		return id;
	}

	public DeliveryOption setId(int id)
	{
		this.id = id;

		return this;
	}
	public int getSort()
	{
		return sort;
	}
	public DeliveryOption setSort(int sort)
	{
		this.sort = sort;

		return this;
	}

	public DeliveryType getType()
	{
		return type;
	}

	public DeliveryOption setType(DeliveryType type)
	{
		this.type = type;

		return this;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public DeliveryOption setServiceName(String serviceName)
	{
		this.serviceName = serviceName;

		return this;
	}

	public double getPrice()
	{
		return price;
	}

	public DeliveryOption setPrice(double price)
	{
		this.price = price;

		return this;
	}

	public Dates getDates()
	{
		return dates;
	}

	public DeliveryOption setDates(Dates dates)
	{
		this.dates = dates;

		return this;
	}

	public List<Outlet> getOutlets()
	{
		return outlets;
	}

	public DeliveryOption setOutlets(List<Outlet> outlets)
	{
		this.outlets = outlets;

		return this;
	}

	@Override
	public int hashCode()
	{
		return getId();
	}
	@Override
	public boolean equals(Object obj)
	{
		return (obj instanceof DeliveryOption && ((DeliveryOption)obj).getId() == id);
	}

	@Override
	public String toString()
	{
		return "DeliveryOption{" +
			"id=" + id +
			", type=" + type +
			", serviceName='" + serviceName + '\'' +
			", price=" + price +
			", dates=" + dates +
			", sort=" + sort +
			", outlets=" + outlets +
			'}';
	}
}
