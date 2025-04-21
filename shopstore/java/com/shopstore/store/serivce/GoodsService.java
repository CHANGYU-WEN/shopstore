package com.shopstore.store.serivce; // 服務層介面

import com.shopstore.store.domain.Goods;

import java.util.List;

public interface GoodsService
{
    List<Goods> queryAll();

    List<Goods> queryByStartEnd(int start, int end);

    Goods queryDetails(long goodsId);
}
