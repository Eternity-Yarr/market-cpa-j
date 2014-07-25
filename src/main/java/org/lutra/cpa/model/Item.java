package org.lutra.cpa.model;

import org.lutra.cpa.service.ItemService;

public class Item
{

	int feedId;
	String offerId;
	String feedCategoryId;
	String offerName;
	double price;
	int count;

	public double getPrice()
	{
		//TODO: 0 price?
		if(price == 0)
			price = ItemService.i().getPrice(offerId);

		return price;
	}

	public double getCount()
	{
		return count;
	}

	public String getOfferId()
	{
		return offerId;
	}
}
