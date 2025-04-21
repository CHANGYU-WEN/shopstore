package com.shopstore.store.serivce.imp;

import com.shopstore.store.dao.GoodsDao;
import com.shopstore.store.dao.OrderDao;
import com.shopstore.store.dao.OrderLineItemDao;
import com.shopstore.store.dao.imp.GoodsDaoImpJdbc;
import com.shopstore.store.dao.imp.OrderDaoImpJdbc;
import com.shopstore.store.dao.imp.OrderLineItemDaoImpJdbc;
import com.shopstore.store.domain.Goods;
import com.shopstore.store.domain.Order;
import com.shopstore.store.domain.OrderLineItem;
import com.shopstore.store.serivce.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderServiceImp implements OrderService
{
    GoodsDao goodsDao = new GoodsDaoImpJdbc();
    OrderDao orderDao = new OrderDaoImpJdbc();
    OrderLineItemDao orderLineItemDao = new OrderLineItemDaoImpJdbc();

    @Override
    public String submitOrder(List<Map<String, Object>> cart)
    {
        Order orders = new Order();
        Date date = new Date();
        String orderId = String.valueOf(date.getTime())+ String.valueOf((int)(Math.random()*100));
        orders.setId(orderId);
        orders.setOrderDate(date);
        orders.setStatus(1);
        orders.setTotal(0.0);

        //把訂單內容插入到數據庫
        orderDao.create(orders);

        double total=0.0;

        for(Map item : cart)
        {
            Long goodsid = (Long)(item.get("goodsid"));
            Integer quantity = (Integer) item.get("quantity");
            Goods goods = goodsDao.findByPk(goodsid);

            //小計=商品數量*商品單價
            double subTotal = goods.getPrice()*quantity;
            total+=subTotal;

            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setQuantity(quantity);
            orderLineItem.setGoods(goods);
            orderLineItem.setOrders(orders);
            orderLineItem.setSubtotal(subTotal);

            //插入數據庫
            orderLineItemDao.create(orderLineItem);
        }

        orders.setTotal(total);
        orderDao.modify(orders);

        return orderId;
    }
}
