package com.shopstore.store.serivce.imp;

import com.shopstore.store.dao.OrderDao;
import com.shopstore.store.dao.OrderLineItemDao;
import com.shopstore.store.dao.imp.OrderDaoImpJdbc;
import com.shopstore.store.dao.imp.OrderLineItemDaoImpJdbc;
import com.shopstore.store.domain.Order;
import com.shopstore.store.domain.OrderLineItem;
import com.shopstore.store.serivce.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImpTest
{
    OrderService orderService;
    OrderDao orderDao = new OrderDaoImpJdbc();
    OrderLineItemDao orderLineItemDao = new OrderLineItemDaoImpJdbc();

    @BeforeEach
    void setUp()
    {
        orderService = new OrderServiceImp();
    }

    @AfterEach
    void tearDown()
    {
        orderService = null;
    }

    @Test
    void submitOrder()
    {
        List<Map<String,Object>> cart  = new ArrayList<Map<String,Object>>();

        //購買第一項
        Map<String,Object> item1 = new HashMap<String,Object>();
        item1.put("goodsId",1L);
        item1.put("quantity",2);
        cart.add(item1);

        //購買第二項
        Map<String,Object> item2 = new HashMap<String,Object>();
        item2.put("goodsId",3L);
        item2.put("quantity",2);
        cart.add(item2);

        String ordersId = orderService.submitOrder(cart);
        assertNotNull(ordersId);

        Order orders = orderDao.findByPk(ordersId);
        assertNotNull(orders);
        assertEquals(1,orders.getStatus());

        //測試品項的金額總和
        double total = 350*2 + 420*2;
        assertEquals(total,orders.getTotal());


        List<OrderLineItem> list = orderLineItemDao.findAll();

        //做一個ArrayList物件，輪巡原本list裡面的元素，加到ArrayList裡，再比較兩個是否相等

        List<OrderLineItem> check = new ArrayList<OrderLineItem>();

        for(OrderLineItem item : list)
        {
            if(item.getOrders().getId().equals(orders.getId()))
            {
                check.add(item);

                if(item.getGoods().getId()==1L)
                {
                    assertEquals(2,item.getQuantity());
                    assertEquals(350*2,item.getSubtotal());
                }

                if(item.getGoods().getId()==3L)
                {
                    assertEquals(2,item.getQuantity());
                    assertEquals(420*2,item.getSubtotal());
                }

            }
        }

        assertEquals(2,check.size());
    }
}