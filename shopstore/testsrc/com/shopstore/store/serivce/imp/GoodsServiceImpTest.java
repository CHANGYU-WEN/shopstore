package com.shopstore.store.serivce.imp;

import com.shopstore.store.domain.Goods;
import com.shopstore.store.serivce.GoodsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoodsServiceImpTest {

    GoodsService goodsService;

    @BeforeEach
    void setUp()
    {
        goodsService = new GoodsServiceImp();
    }

    @AfterEach
    void tearDown()
    {
        goodsService = null;
    }

    @Test
    void queryAll()
    {
        List<Goods> goodsList = goodsService.queryAll();
        assertEquals(15, goodsList.size());

        Goods goods = goodsList.get(8);
        assertEquals("可燃物【橫掃日本推理小說排行榜，年度最強三冠王！】",goods.getBookName());
        assertEquals(9,goods.getId());
        assertEquals(360,goods.getPrice());
        assertEquals("米澤穗信",goods.getAuthor());
        assertEquals("黃涓芳",goods.getTranslator());
        assertEquals("尖端",goods.getPublisher());
        assertEquals(9786264036122L,goods.getIsbn());
        assertEquals("繁體中文",goods.getLanguage());
        assertEquals("可燃物.png",goods.getImage());
        assertEquals(1, goods.getBookTypeId());
    }

    @Test
    void queryByStartEnd()
    {
        List<Goods> goodsList = goodsService.queryByStartEnd(0,10);
        assertEquals(10,goodsList.size());

        Goods goods = goodsList.get(8);
        assertEquals("可燃物【橫掃日本推理小說排行榜，年度最強三冠王！】",goods.getBookName());
        assertEquals(9,goods.getId());
        assertEquals(360,goods.getPrice());
        assertEquals("米澤穗信",goods.getAuthor());
        assertEquals("黃涓芳",goods.getTranslator());
        assertEquals("尖端",goods.getPublisher());
        assertEquals(9786264036122L,goods.getIsbn());
        assertEquals("繁體中文",goods.getLanguage());
        assertEquals("可燃物.png",goods.getImage());
        assertEquals(1, goods.getBookTypeId());
    }

    @Test
    void queryDetails()
    {
        Goods goods =goodsService.queryDetails(9L);
        assertEquals("可燃物【橫掃日本推理小說排行榜，年度最強三冠王！】",goods.getBookName());
        assertEquals(9,goods.getId());
        assertEquals(360,goods.getPrice());
        assertEquals("米澤穗信",goods.getAuthor());
        assertEquals("黃涓芳",goods.getTranslator());
        assertEquals("尖端",goods.getPublisher());
        assertEquals(9786264036122L,goods.getIsbn());
        assertEquals("繁體中文",goods.getLanguage());
        assertEquals("可燃物.png",goods.getImage());
        assertEquals(1, goods.getBookTypeId());
    }
}