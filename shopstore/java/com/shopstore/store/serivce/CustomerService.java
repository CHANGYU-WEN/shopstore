package com.shopstore.store.serivce; // 服務層介面

import com.shopstore.store.domain.Customer;

import java.sql.SQLException;

public interface CustomerService
{
    boolean login(Customer customer);

    //註冊失敗拋出ServiceException 異常
    void register(Customer customer) throws ServiceException;
}
