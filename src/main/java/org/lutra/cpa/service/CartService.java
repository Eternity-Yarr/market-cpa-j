package org.lutra.cpa.service;

import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.model.DeliveryRequest;
import org.lutra.cpa.model.Item;
import org.lutra.cpa.request.post.CartRequest;
import org.lutra.cpa.response.Cart;
import org.lutra.cpa.response.CartResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartService
{
    public static CartResponse process(CartRequest cr)
    {
        DeliveryRequest target = cr.uw().delivery;
        Cart cart = new Cart();
        List<DeliveryOption> deliveryOptions = DeliveryService.getAll(target, cr.uw().getTotal());
        Set<DeliveryOption> feasibleDeliveryOptions = new HashSet<>(deliveryOptions);
        for(Item i : cr.uw().items)
        {
            for(DeliveryOption o : deliveryOptions)
            {
/*                if(!DeliveryService.feasible(i,o))
                    feasibleDeliveryOptions.remove()*/
            }
        }
        return new CartResponse().setCart(cart);
    }
}
