package org.lutra.cpa.model;

public enum OrderSubstatus
{
	RESERVATION_EXPIRED("Покупатель не завершил оформление зарезервированного заказа вовремя"),
	USER_NOT_PAID("Покупатель не оплатил заказ"),
	USER_UNREACHABLE("Не удалось связаться с покупателем"),
	USER_CHANGED_MIND("Покупатель отменил заказ по собственным причинам"),
	USER_REFUSED_DELIVERY("Покупателя не устраивают условия доставки"),
	USER_REFUSED_PRODUCT("Покупателю не подошел товар"),
	SHOP_FAILED("Магазин не может выполнить заказ"),
	USER_REFUSED_QUALITY("Покупателя не устраивает качество товара"),
	REPLACING_ORDER("Покупатель изменяет состав заказа"),
	PROCESSING_EXPIRED("Магазин не обработал заказ вовремя");

	final String desc;
	OrderSubstatus(String desc)
	{
		this.desc = desc;
	}
}
