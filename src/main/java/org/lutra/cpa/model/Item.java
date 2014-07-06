package org.lutra.cpa.model;

public class Item {

    int feedId;
    String offerId;
    String feedCategoryId;
    String offerName;
    double price;
    int count;

    public double getPrice()
    {
        return price;
    }

    public double getCount()
    {
        return count;
    }
}
