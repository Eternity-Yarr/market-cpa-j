package org.lutra.cpa.service;

import org.lutra.cpa.model.OrderStatus;
import org.lutra.cpa.model.OrderSubstatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lutra.cpa.model.OrderStatus.*;
import static org.lutra.cpa.model.OrderSubstatus.*;

public class OrderStatusService
{
    public List<OrderStatus> possibleTransitions(OrderStatus from_status)
    {
        List<OrderStatus> ret = new ArrayList<>();
        switch(from_status)
        {
            case PROCESSING:
                ret = Arrays.asList(DELIVERY, CANCELLED);
                break;
            case DELIVERY:
                ret = Arrays.asList(PICKUP, DELIVERED, CANCELLED);
                break;
            case PICKUP:
                ret = Arrays.asList(DELIVERED, CANCELLED);
            default:
                break;
        }

        return ret;
    }

    public List<OrderSubstatus> possibleSubstatuses(OrderStatus for_status)
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
