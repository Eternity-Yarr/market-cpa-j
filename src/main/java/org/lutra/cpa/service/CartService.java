package org.lutra.cpa.service;

import org.lutra.cpa.Config;
import org.lutra.cpa.model.DeliverableItem;
import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.model.DeliveryType;
import org.lutra.cpa.model.Item;
import org.lutra.cpa.repository.ItemRepository;
import org.lutra.cpa.request.post.CartRequest;
import org.lutra.cpa.response.Cart;
import org.lutra.cpa.response.CartResponse;

import java.util.*;

public class CartService
{
	private static CartService instance;
	public static CartService i()
	{
		if(instance == null)
			instance = new CartService();

		return instance;
	}

	public CartResponse process(CartRequest cr)
	{
		List<DeliverableItem> items = new ArrayList<>();
		Set<Integer> outlets = new HashSet<>();
		for(Integer outlet_id: Config.outlets_mapping.values())
			outlets.add(outlet_id);
		for(Item i : cr.uw().items)
		{
			Set<Integer> item_outlets = new HashSet<>();
			List<Integer> store_ids = ItemRepository.i().getAvailability(i.getOfferId());
			for(Integer store_id: store_ids)
				if (Config.outlets_mapping.get(store_id) != null)
					item_outlets.add(Config.outlets_mapping.get(store_id));
			outlets.retainAll(item_outlets);
			DeliverableItem di =	new DeliverableItem(i)
				.setDelivery(!store_ids.isEmpty());
			items.add(di);
		}
		List<DeliveryOption> deliveryOptions = DeliveryService.i().getFeasible(cr.uw());
		Set<DeliveryOption> feasibleDeliveryOptions = new HashSet<>(deliveryOptions);
		Cart cart = new Cart().setDeliveryOptions(feasibleDeliveryOptions);
		Iterator<DeliveryOption> it = cart.getDeliveryOptions().iterator();
		while(it.hasNext())
		{
			DeliveryOption option = it.next();
			if(option.getType().equals(DeliveryType.PICKUP) && outlets.isEmpty())
				it.remove();
			else if(!outlets.isEmpty())
				option.setOutlets(outlets);
			if(!option.getType().equals(DeliveryType.PICKUP))
				option.setOutlets(null);
		}
		cart.setItems(items);

		return new CartResponse().setCart(cart);
	}
}
