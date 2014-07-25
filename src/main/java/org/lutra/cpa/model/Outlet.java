package org.lutra.cpa.model;

public class Outlet
{
	int id;
	String name;
	OutletType type;
	boolean isMain;
	OutletStatus status;
	String reason;
	OutletVisibility visibility;
	String workingTime;
	String shopOutletId;
	OutletAddress address;

	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}

	@Override
	public int hashCode()
	{
		return id;
	}

	@Override
	public boolean equals(Object obj)
	{
		return (obj instanceof Outlet) && ((Outlet)obj).id  == id;
	}

	// TODO: devliery-rules, emails, phones
	//List<DeliveryRules> deliveryRules;
	//List<Email> emails;
	//List<Phone> phones;
}
