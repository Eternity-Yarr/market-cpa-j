package com.ll.market;

public enum PaymentMethod
{
    SHOP_PREPAID    ("Предоплата напрямую магазину"),
    CASH_ON_DELIVERY("Наличный расчет при получении заказа"),
    CARD_ON_DELIVERY("Оплата банковской картой при получении заказа");

    final String desc;
    PaymentMethod(String desc)
    {
        this.desc = desc;
    }


}
