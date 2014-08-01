package org.lutra.cpa.service.delivery;

import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.repository.BitrixDeliveryRepository;
import org.lutra.cpa.request.post.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class AmountConstraint extends AbstractConstraint implements Callable<Set<DeliveryOption>>
{
	final private static Logger log = LoggerFactory.getLogger(AmountConstraint.class);

	public AmountConstraint(Cart rx)
	{
		super(rx);
	}

	@Override
	public Set<DeliveryOption> call() throws Exception
	{
		Set<DeliveryOption> xs = new HashSet<>();
		xs.addAll(BitrixDeliveryRepository.i().getByTotalAmount(rx.getTotal()));
		log.info("Got {} methods of delivery for amount = {}", xs.size(), rx.getTotal());

		return xs;
	}
}
