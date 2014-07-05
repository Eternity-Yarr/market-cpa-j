package org.lutra.cpa.model;

public enum OrderStatus
{
    UNPAID    ("Заказ офорлен, но еще не оплачен"),
    PROCESSING("Заказ можно выполнять"),
    CANCELLED ("Заказ отменен");

    final String desc;
    OrderStatus(String desc)
    {
        this.desc = desc;
    }
}
