package org.lutra.cpa.service;

import org.lutra.cpa.model.Delivery;
import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.repository.DeliveryRepository;

import java.util.List;

public class DeliveryService
{
    public static List<DeliveryOption> getAll(Delivery target)
    {
        return DeliveryRepository.getAll(target);
    }
}
