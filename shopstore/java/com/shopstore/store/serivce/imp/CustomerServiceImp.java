package com.shopstore.store.serivce.imp; // 服務層介面的實作

import com.shopstore.store.dao.CustomerDao;
import com.shopstore.store.dao.imp.CustomerDaoImpJdbc;
import com.shopstore.store.domain.Customer;
import com.shopstore.store.serivce.CustomerService;
import com.shopstore.store.serivce.ServiceException;

public class CustomerServiceImp implements CustomerService
{
    CustomerDao customerDao = new CustomerDaoImpJdbc();

    @Override
    public boolean login(Customer customer)
    {
        Customer dbCustomer = customerDao.findByPk(customer.getId());
        if(dbCustomer!=null && dbCustomer.getPassword().equals(customer.getPassword()))
        {
            //登入成功，讓資料傳到表示層
            customer.setName(dbCustomer.getName());
            customer.setBirthday(dbCustomer.getBirthday());
            customer.setAddress(dbCustomer.getAddress());
            customer.setPhone(dbCustomer.getPhone());
            return true;
        }
        return false;
    }

    @Override
    public void register(Customer customer) throws ServiceException
    {
        Customer dbCustomer = customerDao.findByPk(customer.getId());
        if(dbCustomer!=null) // ID已存在的話拋出異常
        {
            throw new ServiceException("使用者ID"+customer.getId()+"已存在");
        }
        else // ID不存在的話，可以開始註冊(插入該筆數據，到資料庫)
        {
            customerDao.create(customer);
        }
    }
}
