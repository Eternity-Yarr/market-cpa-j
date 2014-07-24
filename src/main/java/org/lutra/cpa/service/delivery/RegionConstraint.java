package org.lutra.cpa.service.delivery;

import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.model.Region;
import org.lutra.cpa.model.RegionType;
import org.lutra.cpa.repository.DeliveryRepository;
import org.lutra.cpa.request.post.Cart;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class RegionConstraint implements Callable<Set<DeliveryOption>>
{
	final private int MOSCOW_CITY_ID = 213;
	final private Cart rx;
	public RegionConstraint(Cart rx)
	{
		this.rx = rx;
	}

	@Override
	public Set<DeliveryOption> call() throws Exception
	{
		Set<DeliveryOption> xs = new HashSet<>();
		String city_name = null;
		int city_id = 0;
		Region r;
		while((r = rx.delivery.getRegion()) != null)
		{
			if(r.getType() == RegionType.CITY)
			{
				city_name = r.getName();
				city_id = r.getId();
				break;
			}
		}
		if(city_name == null)
		{
			// by region?
		}
		else
		{
			xs.addAll(DeliveryRepository.i().getByCityName(city_name));
		}

		return xs;
	}
}
