package com.ll.market;

public enum PaymentType
{
    PREPAID     ("Предоплата напрямую магазину"),
    POSTPAID    ("Постоплата при получении заказа");

    final String desc;
    PaymentType(String desc)
    {
        this.desc = desc;
    }
}
