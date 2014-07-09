package org.lutra.cpa.model;

public class Address
{
	public String country;
	public String postcode;
	public String city;
	public String subway;
	public String street;
	public String house;
	public String block;
	public String entrance;
	public String entryphone;
	public String floor;
	public String apartment;
	public String recipient;
	public String phone;

	public String getCity()
	{
		return city;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Address))
			return false;
		Address that = (Address)obj;
		return
					this.country.equals(that.country)
			&&	this.postcode.equals(that.postcode)
			&&	this.city.equals(that.city)
			&&	this.subway.equals(that.subway)
			&&	this.street.equals(that.street)
			&&	this.house.equals(that.house)
			&&	this.block.equals(that.block)
			&&	this.entrance.equals(that.entrance)
			&&	this.entryphone.equals(that.entryphone)
			&&	this.floor.equals(that.floor)
			&&	this.apartment.equals(that.apartment)
			&&	this.recipient.equals(that.recipient)
			&&	this.phone.equals(that.phone);

	}
}
