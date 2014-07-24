package org.lutra.cpa.service.delivery;

import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.request.post.Cart;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class RegionConstraint implements Callable<Set<DeliveryOption>>
{
    final private Cart rx;
    public RegionConstraint(Cart rx)
    {
        this.rx = rx;
    }

    @Override
    public Set<DeliveryOption> call() throws Exception
    {
        Set<DeliveryOption> xs = new HashSet<>();
        //rx.delivery.getRegion()
        return xs;
    }
}
