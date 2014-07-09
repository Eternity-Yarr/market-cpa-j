package org.lutra.cpa.model;

public class Region
{

	int id;
	String name;
	RegionType type;
	Region parent;

	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public RegionType getType()
	{
		return type;
	}
	public Region getParent()
	{
		return parent;
	}

}
