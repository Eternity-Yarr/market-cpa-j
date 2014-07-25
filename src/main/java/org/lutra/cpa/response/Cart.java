package org.lutra.cpa.response;

import org.lutra.cpa.model.DeliverableItem;
import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.model.Item;
import org.lutra.cpa.model.PaymentMethod;

import java.util.Collection;
import java.util.List;

/**
 * Structure of POST /cart response
 *
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/post-cart.xml">reference</a>
 */
public class Cart
{
	List<DeliverableItem> items;
	Collection<DeliveryOption> deliveryOptions;
	List<PaymentMethod> paymentMethods;

	public List<DeliverableItem> getItems()
	{
		return items;
	}

	public Cart setItems(List<DeliverableItem> items)
	{
		this.items = items;

		return this;
	}

	public Collection<DeliveryOption> getDeliveryOptions()
	{
		return deliveryOptions;
	}

	public Cart setDeliveryOptions(Collection<DeliveryOption> deliveryOptions)
	{
		this.deliveryOptions = deliveryOptions;

		return this;
	}

	public List<PaymentMethod> getPaymentMethods()
	{
		return paymentMethods;
	}

	public Cart setPaymentMethods(List<PaymentMethod> paymentMethods)
	{
		this.paymentMethods = paymentMethods;

		return this;
	}
}
