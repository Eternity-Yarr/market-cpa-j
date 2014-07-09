package org.lutra.cpa.model;

public enum DeliveryType
{
	DELIVERY("Курьерская доставка"),
	PICKUP("Самовывоз"),
	POST("Почта");

	final String desc;
	DeliveryType(String desc)
	{
		this.desc = desc;
	}
}
