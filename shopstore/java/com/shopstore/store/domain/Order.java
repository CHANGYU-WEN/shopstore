package com.shopstore.store.domain; // 實體類

import java.util.Date;
import java.util.List;

public class Order
{
    private String id;
    private Date orderDate;
    private int status =1; // 1表示待確認，0表示已確認
    private double total;
    private List<OrderLineItem> orderLineItems;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }
    public List<OrderLineItem> getOrderLineItems()
    {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderLineItem> orderLineItems)
    {
        this.orderLineItems = orderLineItems;
    }
}
