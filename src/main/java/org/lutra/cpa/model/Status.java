package org.lutra.cpa.model;


public enum Status
{
    RESERVED        ("Заказ зарезервирован"),
    PROCESSING      ("Заказ находится в обработке"),
    DELIVERY        ("Заказ передан в доставку"),
    PICKUP          ("Заказ доставлен в пункт самовывоза"),
    DELIVERED       ("Заказ получен покупателем"),
    CANCELLED       ("Заказ отменен");

    final public String desc;
    Status(String desc){
        this.desc= desc;
    }
}
