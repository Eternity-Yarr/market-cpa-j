package org.lutra.cpa.model;

import org.lutra.cpa.service.ItemService;

public class DeliverableItem
{

	int feedId;
	String offerId;
	private double price;
	int count;
	boolean delivery;

	public DeliverableItem(Item i)
	{
		feedId = i.feedId;
		offerId = i.offerId;
		count = i.count;
		price =  ItemService.i().getPrice(offerId);
	}

	public double getPrice()
	{
		return price;
	}

	public DeliverableItem setPrice(double price)
	{
		this.price = price;

		return this;
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
