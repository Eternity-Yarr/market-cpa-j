package org.lutra.cpa.model;

public class OutletAddress
{
    String city;
    String number;
    int km;
    String street;
    String block;
    String building;
    String estate;
    String additional;

    @Override
    public String toString()
    {
        return String.format("%s %s %s (%s)", city, street, building, additional); //TODO: Meh..
    }
}
