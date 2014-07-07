package org.lutra.cpa;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelpersTest
{

    @Test
    public void testCreateStatement() throws Exception
    {
        Db.setupDriver();
        String q =
                "SELECT id, name, description, price, order_price_from, order_price_to FROM b_sale_delivery WHERE active = 'Y' AND order_price_from < ? AND id IN (?,?)";
        Object[] params = {500.0, 1, 4};
        try
        (
            Connection con = Db.ds.getConnection();
            PreparedStatement ps = Helpers.createStatement(con, q, params);
            ResultSet rs = ps.executeQuery()
        )
        {
            while(rs.next())
            {
                System.out.println(rs.getString("name"));
            }
        }
    }
}