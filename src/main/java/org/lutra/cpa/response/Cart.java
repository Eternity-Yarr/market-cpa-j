package org.lutra.cpa.response;

import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.model.Item;
import org.lutra.cpa.model.PaymentMethod;

import java.util.List;

/**
 * Structure of POST /cart response
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/post-cart.xml">reference</a>
 */
public class Cart
{
    List<Item> items;
    List<DeliveryOption> deliveryOptions;
    List<PaymentMethod> paymentMethods;
}
