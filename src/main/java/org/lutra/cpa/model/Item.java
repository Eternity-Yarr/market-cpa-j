package org.lutra.cpa.model;

import org.lutra.cpa.service.ItemService;

public class Item
{

	int feedId;
	String offerId;
	String feedCategoryId;
	String offerName;
	double price = 0;
	int count;

	public double getPrice()
	{
		//TODO: 0 price?
		return price == 0 ? ItemService.i().getPrice(offerId) : price;
	}

	public double getCount()
	{
		return count;
	}
}
