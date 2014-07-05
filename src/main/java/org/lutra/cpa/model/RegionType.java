package com.ll.market;

public enum RegionType
{
    REGION          ("Регион"),
    COUNTRY         ("Страна"),
    COUNTRY_DISTRICT("Федеральный округ"),
    REPUBLIC        ("Субъект федерации"),
    REPUBLIC_AREA   ("Район субъекта федерации"),
    CITY            ("Город"),
    VILLAGE         ("Поселок или село"),
    CITY_DISTRICT   ("Район города"),
    SUBWAY_STATION  ("Станция метро"),
    OTHER           ("Дополнительный тип для регоинов, отличных от перечисленных");

    final String desc;
    RegionType(String desc)
    {
        this.desc = desc;
    }
}
