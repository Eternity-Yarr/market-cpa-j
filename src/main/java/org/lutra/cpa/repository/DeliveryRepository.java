package org.lutra.cpa.repository;

import org.lutra.cpa.Db;
import org.lutra.cpa.model.Delivery;
import org.lutra.cpa.model.DeliveryOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRepository
{
    private static Logger log = LoggerFactory.getLogger("DeliveryRepo");
    public static List<DeliveryOption> getAll(Delivery target)
    {
        List<DeliveryOption> options = new ArrayList<>();
        String q = "SELECT ID, NAME, DESCRIPTION, PRICE, ORDER_PRICE_FROM, ORDER_PRICE_TO FROM b_sale_delivery WHERE ACTIVE = 'Y'";
        try
        (
            Connection con = Db.ds.getConnection();
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery()
        )
        {
            while(rs.next())
            {

            }
        }
        catch(Exception e)
        {
            log.error(e.toString(), e);
        }
        //TODO:
        return options;
    }
}
