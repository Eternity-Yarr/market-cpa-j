package org.lutra.cpa.model;

public enum PaymentMethod
{
	SHOP_PREPAID("Предоплата напрямую магазину", "Предоплата"),
	CASH_ON_DELIVERY("Наличный расчет при получении заказа", "При получении"),
	CARD_ON_DELIVERY("Оплата банковской картой при получении заказа", "Картой"),
	YANDEX("Предоплата через Яндекс", "Яндекс");

	final String desc;
	final String brief;
	PaymentMethod(String desc, String brief)
	{
		this.desc = desc;
		this.brief = brief;
	}


}
