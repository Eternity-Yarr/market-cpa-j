package org.lutra.cpa.response;

import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.model.Item;
import org.lutra.cpa.model.PaymentMethod;

import java.util.List;

public class Cart
{
    List<Item> items;
    List<DeliveryOption> deliveryOptions;
    List<PaymentMethod> paymentMethods;
}
