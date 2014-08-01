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
				&& this.postcode.equals(that.postcode)
				&& this.city.equals(that.city)
				&& this.subway.equals(that.subway)
				&& this.street.equals(that.street)
				&& this.house.equals(that.house)
				&& this.block.equals(that.block)
				&& this.entrance.equals(that.entrance)
				&& this.entryphone.equals(that.entryphone)
				&& this.floor.equals(that.floor)
				&& this.apartment.equals(that.apartment)
				&& this.recipient.equals(that.recipient)
				&& this.phone.equals(that.phone);

	}

	@Override
	public int hashCode()
	{
		int result = country != null ? country.hashCode() : 0;
		result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
		result = 31 * result + (city != null ? city.hashCode() : 0);
		result = 31 * result + (subway != null ? subway.hashCode() : 0);
		result = 31 * result + (street != null ? street.hashCode() : 0);
		result = 31 * result + (house != null ? house.hashCode() : 0);
		result = 31 * result + (block != null ? block.hashCode() : 0);
		result = 31 * result + (entrance != null ? entrance.hashCode() : 0);
		result = 31 * result + (entryphone != null ? entryphone.hashCode() : 0);
		result = 31 * result + (floor != null ? floor.hashCode() : 0);
		result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
		result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
		result = 31 * result + (phone != null ? phone.hashCode() : 0);
		return result;
	}
}
