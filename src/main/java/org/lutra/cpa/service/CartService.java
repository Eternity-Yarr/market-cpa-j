package org.lutra.cpa.service;

import org.lutra.cpa.model.DeliverableItem;
import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.model.Item;
import org.lutra.cpa.repository.ItemRepository;
import org.lutra.cpa.request.post.CartRequest;
import org.lutra.cpa.response.Cart;
import org.lutra.cpa.response.CartResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartService
{
	public static CartResponse process(CartRequest cr)
	{
		List<DeliveryOption> deliveryOptions = DeliveryService.i().getFeasible(cr.uw());
		Set<DeliveryOption> feasibleDeliveryOptions = new HashSet<>(deliveryOptions);
		Cart cart = new Cart().setDeliveryOptions(feasibleDeliveryOptions);
		List<DeliverableItem> items = new ArrayList<>();
		for(Item i : cr.uw().items)
		{
			DeliverableItem di = new DeliverableItem(i);
			di.setDelivery(!ItemRepository.i().getAvailability(i.getOfferId()).isEmpty());
			items.add(di);
		}
		cart.setItems(items);

		return new CartResponse().setCart(cart);
	}
}
