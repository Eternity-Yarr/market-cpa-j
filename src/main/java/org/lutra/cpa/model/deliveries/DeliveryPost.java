package org.lutra.cpa.model.deliveries;

import org.lutra.cpa.model.Address;
import org.lutra.cpa.model.Dates;
import org.lutra.cpa.model.DeliveryType;

/**
 * 09.07.2014 at 17:26
 * DeliveryPickup of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class DeliveryPost
{
	public DeliveryType type;
	public String serviceName;
	public Dates dates;
	public Address address;
	public double price;
}
