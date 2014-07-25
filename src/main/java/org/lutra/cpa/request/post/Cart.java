package org.lutra.cpa.request.post;

import org.lutra.cpa.model.Currency;
import org.lutra.cpa.model.DeliveryRequest;
import org.lutra.cpa.model.Item;
import org.lutra.cpa.service.ItemService;

import java.util.List;

/**
 * Structure of POST /cart request
 *
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/post-cart.xml">reference</a>
 */
public class Cart
{
	public Currency currency;
	public List<Item> items;
	public DeliveryRequest delivery;
	private double total = 0;

	public double getTotal()
	{
		double ret = 0;
		for(Item i : items)
		{
			ret += i.getCount() * i.getPrice();
		}
		return total == 0 ? ret : total;
	}
}
