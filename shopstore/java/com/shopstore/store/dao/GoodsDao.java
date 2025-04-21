package com.shopstore.store.dao;//持久層

import com.shopstore.store.domain.Goods;

import java.util.List;

public interface GoodsDao
{
    //查詢商品
    Goods findByPk(long pk);

    //查詢所有商品
    List<Goods> findAllGoods();

    //查詢分頁
    List<Goods> findStartEnd(int start , int end);

    //新增
    void create(Goods goods);

    //修改
    void modify(Goods goods);

    //刪除
    void remove(long pk);
}
