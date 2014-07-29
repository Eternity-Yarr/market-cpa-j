package org.lutra.cpa.model;

public enum OrderStatus
{
	UNPAID("Заказ оформлен, но еще не оплачен", "Не оплачен", "warning"),
	PROCESSING("Заказ можно выполнять", "Выполняется", "danger"),
	CANCELLED("Заказ отменен", "Отменен", ""),
	DELIVERY("Заказ передан в доставку", "Доставка", "warning"),
	PICKUP("Заказ доставлен в пункт самовывоза", "Самовывоз", "warning"),
	DELIVERED("Заказ получен покупателем", "Выполнен", "success");

	final public String desc;
	final public String brief;
	final public String css_class;
	OrderStatus(String desc, String brief, String css_class)
	{
		this.desc = desc;
		this.brief = brief;
		this.css_class = css_class;
	}
}
