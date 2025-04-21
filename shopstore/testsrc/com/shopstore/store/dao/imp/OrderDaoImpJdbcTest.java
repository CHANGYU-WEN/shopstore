package com.shopstore.store.dao.imp;

import com.shopstore.store.dao.OrderDao;
import com.shopstore.store.domain.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoImpJdbcTest
{
    OrderDao dao;
    @BeforeEach
    void setUp()
    {
        dao = new OrderDaoImpJdbc();
    }

    @AfterEach
    void tearDown()
    {
        dao= null;
    }

    @Test
    void findByPk()
    {
        Order orders = dao.findByPk("1");
        assertNotNull(orders);
        assertEquals("1", orders.getId());
        assertEquals(20250415, orders.getOrderDate().getTime());
        assertEquals(1, orders.getStatus());
        assertEquals(100, orders.getTotal());

    }

    @Test
    void findAllOrders()
    {
        List<Order> list = dao.findAllOrders();
        assertNotNull(list);
        assertEquals(list.size(),1);

        Order order2 = list.get(0);
        assertNotNull(order2);
        assertEquals("1", order2.getId());
        assertEquals(20250415, order2.getOrderDate().getTime());
        assertEquals(1, order2.getStatus());
        assertEquals(100, order2.getTotal());
    }

    @Test
    void create()
    {
        Order order = new Order();
        order.setId("2");
        order.setOrderDate(new Date(20250416));
        order.setStatus(1);
        order.setTotal(1000);
        dao.create(order);

        Order order2 = dao.findByPk("2");
        assertNotNull(order2);
        assertEquals("2", order2.getId());
        assertEquals(20250416, order2.getOrderDate().getTime());
        assertEquals(1, order2.getStatus());
        assertEquals(1000, order2.getTotal());

    }

    @Test
    void modify()
    {
        Order order = new Order();
        order.setId("2");
        order.setOrderDate(new Date(20250417));
        order.setStatus(0);
        order.setTotal(3000);
        dao.modify(order);
        Order order2 = dao.findByPk("2");
        assertNotNull(order2);
        assertEquals("2", order2.getId());
        assertEquals(20250417, order2.getOrderDate().getTime());
        assertEquals(0, order2.getStatus());
        assertEquals(3000, order2.getTotal());

    }

    @Test
    void remove()
    {
        dao.remove("2");
        Order order = dao.findByPk("2");
        assertNull(order);
    }
}