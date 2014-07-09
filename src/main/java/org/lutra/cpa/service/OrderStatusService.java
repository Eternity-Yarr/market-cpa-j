package org.lutra.cpa.service;

import org.lutra.cpa.model.DeliveryType;
import org.lutra.cpa.model.OrderStatus;
import org.lutra.cpa.model.OrderSubstatus;

import java.util.*;

import static org.lutra.cpa.model.OrderStatus.*;
import static org.lutra.cpa.model.OrderSubstatus.*;

public class OrderStatusService
{
	public static Set<OrderStatus> possibleTransitions(OrderStatus from_status, DeliveryType for_deliveryType)
	{
		Set<OrderStatus> ret = new HashSet<>();
		switch(from_status)
		{
			case PROCESSING:
				ret.addAll(Arrays.asList(DELIVERY, CANCELLED));
				break;
			case DELIVERY:
				if(for_deliveryType == DeliveryType.PICKUP)
					ret.addAll(Arrays.asList(PICKUP, CANCELLED));
				else if(for_deliveryType == DeliveryType.DELIVERY)
					ret.addAll(Arrays.asList(DELIVERED, CANCELLED));
				break;
			case PICKUP:
				ret.addAll(Arrays.asList(DELIVERED, CANCELLED));
			default:
				break;
		}

		return ret;
	}

	public static List<OrderSubstatus> possibleCancellationReasons(OrderStatus for_status)
	{
		List<OrderSubstatus> ret = new ArrayList<>();
		switch(for_status)
		{
			case PROCESSING:
				ret = Arrays.asList(USER_UNREACHABLE, USER_CHANGED_MIND, USER_REFUSED_DELIVERY, USER_REFUSED_PRODUCT, SHOP_FAILED, REPLACING_ORDER);
				break;
			case DELIVERY:
			case PICKUP:
				ret = Arrays.asList(USER_UNREACHABLE, USER_CHANGED_MIND, USER_REFUSED_DELIVERY, USER_REFUSED_PRODUCT, USER_REFUSED_QUALITY, SHOP_FAILED);
				break;
			default:
				break;
		}

		return ret;
	}
}
