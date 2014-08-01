package org.lutra.cpa.repository;

import org.lutra.cpa.model.DeliveryOption;

import java.util.List;

/**
 * 01.08.2014 at 19:02
 * DeliveryRepository of market-cpa project
 *
 * Feel free to add new methods which fit your needs
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public interface DeliveryRepository
{
	/**
	 *
	 * @return all available delivery options
	 */
	List<DeliveryOption> getAll();
	/**
	 * @param city_name name of destination city
	 * @return delivery options available for this particular city
	 */
	List<DeliveryOption> getByCityName(String city_name);
	/**
	 * @param total value of order
	 * @return delivery options available for this amount
	 */
	List<DeliveryOption> getByTotalAmount(double total);
}
