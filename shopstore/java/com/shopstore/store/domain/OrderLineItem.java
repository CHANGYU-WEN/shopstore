package com.shopstore.store.domain; // 實體類

public class OrderLineItem
{
    public long id;
    public int quantity;
    public double subtotal;
    public Order order;
    public Goods goods;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(double subtotal)
    {
        this.subtotal = subtotal;
    }

    public Order getOrders()  {
        return order;
    }

    public void setOrders(Order order)
    {
        this.order = order;
    }

    public Goods getGoods()
    {
        return goods;
    }

    public void setGoods(Goods goods)
    {
        this.goods = goods;
    }
}
