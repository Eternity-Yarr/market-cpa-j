package org.lutra.cpa.model;

public enum OrderStatus
{
    UNPAID    ("Заказ оформлен, но еще не оплачен", "Не оплачены"),
    PROCESSING("Заказ можно выполнять", "Выполнять"),
    CANCELLED ("Заказ отменен", "Отменены"),
    DELIVERY  ("Заказ передан в доставку", "В доставке"),
    PICKUP    ("Заказ доставлен в пункт самовывоза", "В самовывозе"),
    DELIVERED ("Заказ получен покупателем", "Выполнены");

    final String desc;
    final String brief;
    OrderStatus(String desc, String brief)
    {
        this.desc = desc;
        this.brief = brief;
    }
}
