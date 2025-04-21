package com.shopstore.store.dao;//持久層

import com.shopstore.store.domain.OrderLineItem;

import java.util.List;

public interface OrderLineItemDao
{

    OrderLineItem findByPk(long pk); //要跟實體類對應
    List<OrderLineItem> findAll();

    void create(OrderLineItem lineItem);
    void modify(OrderLineItem lineItem);
    void remove(int pk);

}
