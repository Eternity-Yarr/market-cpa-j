package org.lutra.cpa.service;

import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.model.DeliveryRequest;
import org.lutra.cpa.repository.DeliveryRepository;

import java.util.List;

public class DeliveryService
{
	static DeliveryRepository dr = new DeliveryRepository(); //TODO: interface

	/**
	 * @param target destination of delivery
	 * @param total  total sum of order
	 * @return list of all available delivery options
	 */
	public static List<DeliveryOption> getAll(DeliveryRequest target, double total)
	{
		return dr.getAll(target, total);
	}
}
