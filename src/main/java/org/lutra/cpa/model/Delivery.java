package org.lutra.cpa.model;

public class Delivery
{
	public String id;
	public DeliveryType type;
	public String serviceName;
	public double price;
	public int outletId;
	public Address address;
	public Dates dates;

	public boolean is_post()
	{
		return type == DeliveryType.POST;
	}
	public boolean is_pickup()
	{
		return type == DeliveryType.PICKUP;
	}
	public boolean is_delivery()
	{
		return type == DeliveryType.DELIVERY;
	}

	@Override
	public String toString()
	{
		return String.format("Delivery#%s price=%s name=%s", type, price, serviceName);
	}

	public int getOutletId()
	{
		return outletId;
	}

	public Address getAddress()
	{
		return address;
	}
	public DeliveryType getType()
	{
		return type;
	}
}
