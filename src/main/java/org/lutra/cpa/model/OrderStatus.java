package org.lutra.cpa.model;

public enum OrderStatus
{
    UNPAID    ("Заказ оформлен, но еще не оплачен", "Не оплачен"),
    PROCESSING("Заказ можно выполнять", "Выполнен"),
    CANCELLED ("Заказ отменен", "Отменен"),
    DELIVERY  ("Заказ передан в доставку", "Доставка"),
    PICKUP    ("Заказ доставлен в пункт самовывоза", "Самовывоз"),
    DELIVERED ("Заказ получен покупателем", "Выполнен");

    final String desc;
    final String brief;
    OrderStatus(String desc, String brief)
    {
        this.desc = desc;
        this.brief = brief;
    }
}
