package com.shopstore.store.dao.imp;


import com.shopstore.JDBCTemplate;
import com.shopstore.store.dao.*;
import com.shopstore.store.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineItemDaoImpJdbcTest
{
    OrderLineItemDao dao;

    @BeforeEach
    void setUp()
    {
        dao = new OrderLineItemDaoImpJdbc();
    }

    @AfterEach
    void tearDown()
    {
        dao = null;
    }

    @Test
    void findByPk()
    {
        OrderLineItem lineItem = dao.findByPk(1);
        assertNotNull(lineItem);
        assertEquals(1,lineItem.getId());
        assertEquals(5,lineItem.getGoods().getId());
        assertEquals("1",lineItem.getOrders().getId());
        assertEquals(5,lineItem.getQuantity());
        assertEquals(1900,lineItem.getSubtotal());
    }

    @Test
    void findAll()
    {
        List<OrderLineItem> list = dao.findAll();
        assertNotNull(list);
        assertEquals(1,list.size());

        OrderLineItem lineItem = new OrderLineItem();
        lineItem = dao.findByPk(1);
        assertNotNull(lineItem);
        assertEquals(1,lineItem.getId());
        assertEquals(5,lineItem.getGoods().getId());
        assertEquals("1",lineItem.getOrders().getId());
        assertEquals(5,lineItem.getQuantity());
        assertEquals(1900,lineItem.getSubtotal());

    }

    @Test
    void create()
    {

        // 做LineItem 的物件
        OrderLineItem lineItem = new OrderLineItem();

        //先增加order的資料，因為有外鍵
        Order order = new Order();
        order.setId("10");
        order.setOrderDate(new Date(20250418));
        order.setStatus(1);
        order.setTotal(3500);
        OrderDao orderDao = new OrderDaoImpJdbc();
        orderDao.create(order);
        lineItem.setOrders(order);

        //先增加goods的資料，因為有外鍵
        Goods goods = new Goods();
        goods.setId(997);
        goods.setBookName("不務正業的博物館：窺見30件超有戲文物的祕辛");
        goods.setPrice(390);
        goods.setAuthor("郭怡汝");
        goods.setTranslator("");
        goods.setPublisher("如何");
        goods.setIsbn(9789861367307L);
        goods.setLanguage("繁體中文");
        goods.setImage("不務正業的博物館.png");
        goods.setBookTypeId(3);

        GoodsDao goodsDao = new GoodsDaoImpJdbc();
        goodsDao.create(goods);
        lineItem.setGoods(goods);

        //設定LineItem的資料
        lineItem.setId(2);
        lineItem.setQuantity(3);
        lineItem.setSubtotal(1140);

        dao.create(lineItem);
        OrderLineItem lineItem1 = dao.findByPk(2);

        assertNotNull(lineItem1);
        assertEquals(2,lineItem1.getId());
        assertEquals(997,lineItem1.getGoods().getId());
        assertEquals("10",lineItem1.getOrders().getId());
        assertEquals(3,lineItem1.getQuantity());
        assertEquals(1140,lineItem1.getSubtotal());

    }

    @Test
    void modify()
    {
        // 做LineItem 的物件
        OrderLineItem lineItem = new OrderLineItem();

        //先增加order的資料，因為有外鍵
        Order order = new Order();
        order.setId("10");
        order.setOrderDate(new Date(20250418));
        order.setStatus(0);
        order.setTotal(3600);
        OrderDao orderDao = new OrderDaoImpJdbc();
        orderDao.modify(order);
        lineItem.setOrders(order);

        //先增加goods的資料，因為有外鍵
        Goods goods = new Goods();
        goods.setId(997);
        goods.setBookName("不務正業的博物館：窺見30件超有戲文物的祕辛");
        goods.setPrice(399);
        goods.setAuthor("郭怡汝->修改後");
        goods.setTranslator("");
        goods.setPublisher("如何");
        goods.setIsbn(9789861367305L);
        goods.setLanguage("繁體中文");
        goods.setImage("不務正業的博物館.png");
        goods.setBookTypeId(3);

        GoodsDao goodsDao = new GoodsDaoImpJdbc();
        goodsDao.modify(goods);
        lineItem.setGoods(goods);

        //設定LineItem的資料
        lineItem.setId(2);
        lineItem.setQuantity(4);
        lineItem.setSubtotal(1200);

        dao.modify(lineItem);
        OrderLineItem lineItem1 = dao.findByPk(2);

        assertNotNull(lineItem1);
        assertEquals(2,lineItem1.getId());
        assertEquals(997,lineItem1.getGoods().getId());
        assertEquals("10",lineItem1.getOrders().getId());
        assertEquals(4,lineItem1.getQuantity());
        assertEquals(1200,lineItem1.getSubtotal());
    }

    @Test
    void remove()
    {
        dao.remove(10);
        OrderLineItem lineItem = dao.findByPk(10);
        assertNull(lineItem);
    }
}