package org.lutra.cpa.model;

import org.lutra.cpa.service.ItemService;

public class DeliverableItem
{

	int feedId;
	String offerId;
	double price = 0;
	int count;
	boolean delivery;

	public DeliverableItem(Item i)
	{
		feedId = i.feedId;
		offerId = i.offerId;
		count = i.count;
	}

	public double getPrice()
	{
		//TODO: 0 price?
		return price == 0 ? ItemService.i().getPrice(offerId) : price;
	}

	public double getCount()
	{
		return count;
	}

	public boolean isDelivery()
	{
		return delivery;
	}

	public DeliverableItem setDelivery(boolean delivery)
	{
		this.delivery = delivery;

		return this;
	}

}
