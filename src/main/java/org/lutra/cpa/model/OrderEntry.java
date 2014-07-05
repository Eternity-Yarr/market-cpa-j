package com.ll.market;

import java.util.Date;
import java.util.List;

public class OrderEntry {

    int id;
    Status status;
    SubStatus substatus;
    Date creationDate;
    Currency currency;
    double itemsTotal;
    double total;
    PaymentType paymentType;
    PaymentMethod paymentMethod;
    boolean fake;
    String notes;
    List<Item> items;
    Delivery delivery;
    Buyer buyer;

}
