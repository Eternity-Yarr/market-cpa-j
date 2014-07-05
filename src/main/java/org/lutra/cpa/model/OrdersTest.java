package com.ll.market;

import com.google.gson.*;
import org.junit.Test;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class OrdersTest {

    @Test
    public void testMarketDeserialize(){

        String s = "{\n" +
                " \"pager\":{\"total\":2,\"from\":1,\"to\":2,\"pageSize\":2,\"pagesCount\":1,\"currentPage\":1},\n" +
                " \"orders\":\n" +
                "    [\n" +
                "    {\"id\":12345, \"status\":\"PROCESSING\", \"creationDate\":\"01-02-2013 16:35:03\", \"currency\":\"RUR\", \"itemsTotal\":3700, \"total\":4050, \"paymentType\":\"POSTPAID\", \"paymentMethod\":\"CASH_ON_DELIVERY\", \"fake\":false, \"items\":\n" +
                "        [\n" +
                "        {\"feedId\":12345, \"offerId\":\"4609283881\", \"feedCategoryId\":\"35\", \"offerName\":\"Чайник электрический 100W\", \"price\":1500, \"count\":1},\n" +
                "        {\"feedId\":12345, \"offerId\":\"4607632101\", \"feedCategoryId\":\"41\", \"offerName\":\"Тостер\", \"price\":2200, \"count\":1}\n" +
                "         ],\n" +
                "     \"delivery\":{\"type\":\"DELIVERY\", \"serviceName\":\"СПСР\", \"price\":350, \n" +
                "         \"region\":\n" +
                "            {\"id\":213, \"name\":\"Москва\", \"type\":\"CITY\", \"parent\":\n" +
                "                {\"id\":1, \"name\":\"Москва и Московская область\", \"type\":\"REPUBLIC\", \"parent\":\n" +
                "                    {\"id\":3, \"name\":\"Центр\", \"type\":\"COUNTRY_DISTRICT\", \"parent\":\n" +
                "                        {\"id\":225, \"name\":\"Россия\", \"type\":\"COUNTRY\"}\n" +
                "                    }\n" +
                "                 }\n" +
                "             },\n" +
                "         \"address\":{\"country\":\"Россия\", \"postcode\":\"119313\", \"city\":\"Москва\", \"subway\":\"Проспект Вернадского\", \"street\":\"Ленинский проспект\", \"house\":\"90\", \"entrance\":\"10\", \"entryphone\":\"289\", \"floor\":\"6\", \"apartment\":\"289\", \"recipient\": \"Иванов Иван\", \"phone\": \"+71234567890\"},\n" +
                "         \"dates\": {\"fromDate\": \"02-02-2013\", \"toDate\": \"03-02-2013\"}\n" +
                "       },\n" +
                "     \"buyer\":{\"id\":\"LEgMQuuxR8\", \"lastName\":\"Иванов\", \"firstName\":\"Иван\", \"middleName\":\"Иванович\", \"phone\":\"+71234567890\", \"email\":\"ivanov.ivan@yandex.ru\"}\n" +
                "    },\n" +
                "    {\"id\":12346, \"status\":\"PICKUP\", \"creationDate\":\"31-01-2013 21:42:12\", \"currency\":\"RUR\", \"itemsTotal\":\"30800\", \"total\":\"30800\", \"paymentType\":\"POSTPAID\", \"paymentMethod\":\"CARD_ON_DELIVERY\", \"fake\":false, \"items\":\n" +
                "        [\n" +
                "        {\"feedId\":12345, \"offerId\":\"4638734829\", \"feedCategoryId\":\"22\", \"offerName\":\"Sony Xperia Z\", \"price\":28900, \"count\":1},\n" +
                "        {\"feedId\":12345, \"offerId\":\"4683949391\", \"feedCategoryId\":\"23\", \"offerName\":\"Карта памяти microSDHC 32GB\", \"price\":1900, \"count\":1}\n" +
                "        ],\n" +
                "     \"delivery\":{\"type\":\"PICKUP\", \"price\":0, \"serviceName\":\"Собственная служба доставки\", \"outletId\":9,\n" +
                "         \"region\":\n" +
                "            {\"id\":213, \"name\":\"Москва\", \"type\":\"CITY\", \"parent\":\n" +
                "                {\"id\":1, \"name\":\"Москва и Московская область\", \"type\":\"REPUBLIC\", \"parent\":\n" +
                "                    {\"id\":3, \"name\":\"Центр\", \"type\":\"COUNTRY_DISTRICT\", \"parent\":\n" +
                "                        {\"id\":225, \"name\":\"Россия\", \"type\":\"COUNTRY\"}\n" +
                "                     }\n" +
                "                 }\n" +
                "             },\n" +
                "         \"dates\": {\"fromDate\": \"02-02-2013\", \"toDate\": \"02-02-2013\"}\n" +
                "     },\n" +
                "     \"buyer\":{\"id\":\"LEgMQuuxR10\", \"lastName\":\"Петров\", \"firstName\":\"Петр\", \"middleName\":\"Петрович\", \"phone\":\"+70987654321\", \"email\":\"petrov.petr@yandex.ru\"}\n" +
                "    }\n" +
                "    ]\n" +
                "}";

        GsonBuilder gb = new GsonBuilder().serializeNulls().setPrettyPrinting();
        gb.registerTypeAdapter
        (
            Date.class, new JsonSerializer<Date>()
            {
                @Override
                public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context)
                {
                    final DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    return new JsonPrimitive(fmt.format(src));
                }
            }
        );
        gb.registerTypeAdapter
        (
                Date.class, new JsonDeserializer<Date>()
            {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    final DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    Date d = null;
                    try
                    {
                        d = json == null ?  null : fmt.parse(json.getAsString());
                    }
                    catch (ParseException e){}
                    return d;
                }
            }
        );
        Gson g = gb.create();
        Orders o = g.fromJson(s, Orders.class);
        System.out.println(o);
        assertTrue(o!=null);
        System.out.println(g.toJson(o));
    }
}
