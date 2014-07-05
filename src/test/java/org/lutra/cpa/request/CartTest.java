package org.lutra.cpa.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;
import org.lutra.cpa.wrapper.GsonRootSkipper;

import static org.junit.Assert.*;

/**
 * Serialize/deserialize test of POST /cart request object
 */
public class CartTest
{
    @Test
    public void testCartRequestDeserialize()
    {
/*
{
  "cart":
  {
    "currency":"RUR",
    "items":
    [
    {"feedId":12345, "offerId":"4609283881", "offerName":"Чайник электрический 100W", "count":1, "feedCategoryId":"35"},
    {"feedId":12346, "offerId":"4607632101", "offerName":"Тостер", "count":1, "feedCategoryId":"35"}
     ],
     "delivery":
     {"region":
       {"id":213, "name":"Москва", "type":"CITY", "parent":
          {"id":1, "name":"Москва и Московская область", "type":"REPUBLIC", "parent":
             {"id":3, "name":"Центр", "type":"COUNTRY_DISTRICT", "parent":
                {"id":225, "name":"Россия", "type":"COUNTRY"}
             }
          }
        },
     "address":{"country":"Россия", "postcode":"119313", "city":"Москва", "subway":"Проспект Вернадского", "street":"Ленинский проспект", "house":"90", "floor":"6"}
     }
  }
}
*/
        String json =
            "{\n" +
            "  \"cart\":\n" +
            "  {\n" +
            "    \"currency\":\"RUR\", \n" +
            "    \"items\":\n" +
            "    [\n" +
            "    {\"feedId\":12345, \"offerId\":\"4609283881\", \"offerName\":\"Чайник электрический 100W\", \"count\":1, \"feedCategoryId\":\"35\"},\n" +
            "    {\"feedId\":12346, \"offerId\":\"4607632101\", \"offerName\":\"Тостер\", \"count\":1, \"feedCategoryId\":\"35\"}\n" +
            "     ],\n" +
            "     \"delivery\":\n" +
            "     {\"region\":\n" +
            "       {\"id\":213, \"name\":\"Москва\", \"type\":\"CITY\", \"parent\":\n" +
            "          {\"id\":1, \"name\":\"Москва и Московская область\", \"type\":\"REPUBLIC\", \"parent\":\n" +
            "             {\"id\":3, \"name\":\"Центр\", \"type\":\"COUNTRY_DISTRICT\", \"parent\":\n" +
            "                {\"id\":225, \"name\":\"Россия\", \"type\":\"COUNTRY\"}\n" +
            "             }\n" +
            "          }\n" +
            "        },\n" +
            "     \"address\":{\"country\":\"Россия\", \"postcode\":\"119313\", \"city\":\"Москва\", \"subway\":\"Проспект Вернадского\", \"street\":\"Ленинский проспект\", \"house\":\"90\", \"floor\":\"6\"}\n" +
            "     }\n" +
            "  }\n" +
            "}";
        Cart c = GsonRootSkipper.getFromJSON(json, Cart.class);
        System.out.println(c);
    }
}