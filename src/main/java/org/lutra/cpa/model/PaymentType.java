package org.lutra.cpa.model;

public enum PaymentType
{
	PREPAID("Предоплата напрямую магазину"),
	POSTPAID("Постоплата при получении заказа");

	final String desc;
	PaymentType(String desc)
	{
		this.desc = desc;
	}
}
