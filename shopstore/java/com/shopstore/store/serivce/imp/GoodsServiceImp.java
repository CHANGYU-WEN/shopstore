package com.shopstore.store.serivce.imp; // 服務層介面的實作

import com.shopstore.store.dao.GoodsDao;
import com.shopstore.store.dao.imp.GoodsDaoImpJdbc;
import com.shopstore.store.domain.Goods;
import com.shopstore.store.serivce.GoodsService;

import java.util.List;

public class GoodsServiceImp  implements GoodsService
{
    GoodsDao goodsDao = new GoodsDaoImpJdbc();

    @Override
    public List<Goods> queryAll()
    {
        return goodsDao.findAllGoods();
    }

    @Override
    public List<Goods> queryByStartEnd(int start, int end)
    {
        return goodsDao.findStartEnd(start, end);
    }

    @Override
    public Goods queryDetails(long goodsId)
    {
        return goodsDao.findByPk(goodsId);
    }
}
