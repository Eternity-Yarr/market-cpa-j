package org.lutra.cpa.repository;

import org.junit.Test;
import org.lutra.cpa.Db;
import org.lutra.cpa.Main;
import org.lutra.cpa.model.Address;
import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.model.DeliveryRequest;
import org.lutra.cpa.model.Region;

import java.util.List;

public class DeliveryRepositoryTest
{
    @Test
    public void testGetAll() throws Exception
    {
        String region_json =
                "{\"id\":213, \"name\":\"Москва\", \"type\":\"CITY\", \"parent\":\n" +
                "          {\"id\":1, \"name\":\"Москва и Московская область\", \"type\":\"REPUBLIC\", \"parent\":\n" +
                "             {\"id\":3, \"name\":\"Центр\", \"type\":\"COUNTRY_DISTRICT\", \"parent\":\n" +
                "                {\"id\":225, \"name\":\"Россия\", \"type\":\"COUNTRY\"}\n" +
                "             }\n" +
                "          }\n" +
                "        }";
        String address_json =
                "{\"country\":\"Россия\", \"postcode\":\"119313\", \"city\":\"Москва\", \"subway\":\"Проспект Вернадского\", \"street\":\"Ленинский проспект\", \"house\":\"90\", \"floor\":\"6\"}";
        Db.setupDriver();
        DeliveryRepository dr = new DeliveryRepository();
        Region region = Main.g.fromJson(region_json, Region.class);
        Address address = Main.g.fromJson(address_json, Address.class);
        DeliveryRequest target = new DeliveryRequest()
            .setAddress(address)
            .setRegion(region)
        ;
        List<DeliveryOption> options = dr.getAll();

        Db.ds.close();
    }
}