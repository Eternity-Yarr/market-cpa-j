package org.lutra.cpa.model;

public class DeliveryRequest
{
    Region region;
    Address address;

    public Address getAddress()
    {
        return address;
    }

    public Region getRegion()
    {
        return region;
    }

    public DeliveryRequest setRegion(Region region)
    {
        this.region = region;

        return this;
    }

    public DeliveryRequest setAddress(Address address)
    {
        this.address = address;

        return this;
    }
}
