package org.lutra.cpa.model;

public enum OrderStatus
{
    UNPAID    ("Заказ офорлен, но еще не оплачен"),
    PROCESSING("Заказ можно выполнять"),
    CANCELLED ("Заказ отменен"),
    DELIVERY  ("Заказ передан в доставку"),
    PICKUP    ("Заказ доставлен в пункт самовывоза"),
    DELIVERED ("Заказ получен покупателем");

    final String desc;
    OrderStatus(String desc)
    {
        this.desc = desc;
    }
}
