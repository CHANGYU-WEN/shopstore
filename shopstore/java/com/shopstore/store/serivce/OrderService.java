package com.shopstore.store.serivce;

import java.util.List;
import java.util.Map;

public interface OrderService
{
    String submitOrder(List<Map<String,Object>> cart);
}
