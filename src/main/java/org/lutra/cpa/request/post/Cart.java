package org.lutra.cpa.request.post;

import org.lutra.cpa.model.Currency;
import org.lutra.cpa.model.Delivery;
import org.lutra.cpa.model.Item;

import java.util.List;

/**
 * Structure of POST /cart request
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/post-cart.xml">reference</a>
 */
public class Cart
{
    public Currency currency;
    public List<Item> items;
    public Delivery delivery;
    private double total;

    public double getTotal()
    {
        double ret = 0;
        for(Item i : items)
        {
            ret = i.getCount() * i.getPrice();
        }
        return total;
    }
}
