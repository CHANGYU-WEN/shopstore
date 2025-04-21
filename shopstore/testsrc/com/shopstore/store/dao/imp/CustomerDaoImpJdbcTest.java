package com.shopstore.store.dao.imp;

import com.shopstore.store.dao.CustomerDao;
import com.shopstore.store.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoImpJdbcTest
{
    CustomerDao dao;
    @BeforeEach
    void setUp()
    {
        dao = new CustomerDaoImpJdbc();
    }

    @AfterEach
    void tearDown()
    {
        dao =null;
    }

    @Test
    void findByPk()
    {
        Customer customer = dao.findByPk("krystal");
        assertNotNull(customer);
        assertEquals("krystal", customer.getId());
        assertEquals("張瑜雯", customer.getName());
        assertEquals("1111",customer.getPassword());
        assertEquals("台中市西區", customer.getAddress());
        assertEquals("0912345678", customer.getPhone());
        assertEquals(19951108L,customer.getBirthday().getTime());
    }

    @Test
    void findAll()
    {
        List<Customer> list = dao.findAll();
        assertEquals(list.size(),1);
    }

    @Test
    void create()
    {
        Customer customer = new Customer();
        customer.setId("Chang");
        customer.setName("張小姐");
        customer.setPassword("123");
        customer.setAddress("台中市南區");
        customer.setPhone("0923456789");
        customer.setBirthday( new Date(20250414L));

        dao.create(customer);
        Customer customer2 = dao.findByPk("Chang");
        assertEquals("Chang",customer2.getId());
        assertEquals("張小姐",customer2.getName());
        assertEquals("123",customer2.getPassword());
        assertEquals("台中市南區",customer2.getAddress());
        assertEquals("0923456789",customer2.getPhone());
        assertEquals(20250414L,customer2.getBirthday().getTime());
    }

    @Test
    void modify()
    {
        Customer customer = new Customer();
        customer.setId("Chang");
        customer.setName("張小姐->修改成功");
        customer.setPassword("12345");
        customer.setAddress("台中市南區");
        customer.setPhone("0987654321");
        customer.setBirthday( new Date(20250414L));

        dao.modify(customer);
        Customer customer2 = dao.findByPk("Chang");
        assertEquals("Chang",customer2.getId());
        assertEquals("張小姐->修改成功",customer2.getName());
        assertEquals("12345",customer2.getPassword());
        assertEquals("台中市南區",customer2.getAddress());
        assertEquals("0987654321",customer2.getPhone());
        assertEquals(20250414L,customer2.getBirthday().getTime());


    }

    @Test
    void remove()
    {
        dao.remove("Chang");
        Customer customer2 = dao.findByPk("Chang");
        assertNull(customer2);
    }
}