package org.lutra.cpa.model;

public enum PaymentMethod
{
    SHOP_PREPAID    ("Предоплата напрямую магазину"),
    CASH_ON_DELIVERY("Наличный расчет при получении заказа"),
    CARD_ON_DELIVERY("Оплата банковской картой при получении заказа"),
    YANDEX          ("Предоплата через Яндекс");

    final String desc;
    PaymentMethod(String desc)
    {
        this.desc = desc;
    }


}
