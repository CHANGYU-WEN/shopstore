package com.shopstore.store.dao; // 持久層

import com.shopstore.store.domain.Order;

import java.util.List;

public interface OrderDao
{
    //查詢訂單
    Order findByPk(String pk);

    //查詢所有訂單
    List<Order> findAllOrders();

    //新增
    void create(Order order);

    //修改
    void modify(Order order);

    //刪除
    void remove(String pk);
}
