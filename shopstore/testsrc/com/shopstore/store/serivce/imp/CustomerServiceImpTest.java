package com.shopstore.store.serivce.imp;

import com.shopstore.store.domain.Customer;
import com.shopstore.store.serivce.ServiceException;
import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImpTest
{
    CustomerServiceImp service;
    @BeforeEach
    void setUp()
    {
        service = new CustomerServiceImp();
    }

    @AfterEach
    void tearDown()
    {
        service = null;
    }

    @Test
    @DisplayName("登入成功")
    void login1()
    {
        Customer customer = new Customer();
        customer.setId("krystal");
        customer.setPassword("1111");
        assertTrue(service.login(customer));
        assertNotNull(customer);

        assertEquals("krystal", customer.getId());
        assertEquals("張瑜雯", customer.getName());
        assertEquals("1111", customer.getPassword());
        assertEquals("0912345678", customer.getPhone());
        assertEquals("台中市西區", customer.getAddress());
        assertEquals(19951108L, customer.getBirthday().getTime());

    }

    @Test
    @DisplayName("登入失敗")
    void login2()
    {
        Customer customer = new Customer();
        customer.setId("krystal");
        customer.setPassword("2222");
        assertFalse(service.login(customer));
    }

    @Test
    @DisplayName("註冊成功")
    void register1() throws ServiceException
    {
        Customer customer = new Customer();
        customer.setId("krystal***");
        customer.setName("張小姐");
        customer.setPassword("123456");
        customer.setAddress("台中市南區");
        customer.setPhone("0923456789");
        customer.setBirthday(new Date(20250415L));

        service.register(customer);

        // 調用同一層的方法，再測試一次新註冊的帳號能不能登入，可以登入的話，代表數據庫裡已插入新註冊的帳號
        Customer customer1 = new Customer();
        customer1.setId("krystal***");
        customer1.setPassword("123456");
        service.login(customer1);
        assertTrue(service.login(customer1));
        assertNotNull(customer1);

        assertEquals("krystal***", customer1.getId());
        assertEquals("張小姐", customer1.getName());
        assertEquals("123456", customer1.getPassword());
        assertEquals("0923456789", customer1.getPhone());
        assertEquals("台中市南區", customer1.getAddress());
        assertEquals(20250415L, customer1.getBirthday().getTime());
    }

    @Test
    @DisplayName("ID已存在，註冊失敗")
    void register2() throws ServiceException
    {
        Customer customer1 = new Customer();
        customer1.setId("krystal***");
        customer1.setPassword("123456");

        //斷言拋出異常
        assertThrows(ServiceException.class, () -> service.register(customer1));


    }
}