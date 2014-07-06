package org.lutra.cpa.model;

import java.util.List;

public class DeliveryOption
{
    int id;
    DeliveryType type;
    String serviceName;
    double price;
    Dates dates;
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
}
