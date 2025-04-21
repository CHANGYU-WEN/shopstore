package com.shopstore.store.dao; // 持久層

import com.shopstore.store.domain.Customer;

import java.util.List;

public interface CustomerDao
{
    //查詢客戶
    Customer findByPk (String pk);

    //查詢所有客戶
    List<Customer> findAll();

    //新增
    void create(Customer customer);

    //修改
    void modify(Customer customer);

    //刪除
    void remove(String pk);
}
