package org.lutra.cpa.model;

import java.util.List;

public class DeliveryOption
{
    int id;
    DeliveryType type;
    String serviceName;
    double price;
    Dates dates;
    List<Outlet> outlets;
}
