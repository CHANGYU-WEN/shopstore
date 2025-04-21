package com.shopstore.store.dao.imp;

import com.shopstore.store.dao.GoodsDao;
import com.shopstore.store.domain.Customer;
import com.shopstore.store.domain.Goods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoodsDaoImpJdbcTest
{
    GoodsDao dao;
    @BeforeEach
    void setUp()
    {
        dao = new GoodsDaoImpJdbc();
    }

    @AfterEach
    void tearDown()
    {
        dao =null;
    }

    @Test
    void findByPk()
    {
        Goods goods = dao.findByPk(1L);
        assertNotNull(goods);
        assertEquals(1L, goods.getId());
        assertEquals("又是庸庸碌碌的一天？重整生活的時間管理術",goods.getBookName());
        assertEquals(350,goods.getPrice());
        assertEquals("Swan",goods.getAuthor());
        assertEquals("甘為治",goods.getTranslator());
        assertEquals("楓書坊",goods.getPublisher());
        assertEquals(9789863779216L, goods.getIsbn());
        assertEquals("繁體中文",goods.getLanguage());
        assertEquals("又是庸庸碌碌的一天？.png",goods.getImage());
        assertEquals(7,goods.getBookTypeId());
    }

    @Test
    void findAllGoods()
    {
        List<Goods> list =dao.findAllGoods();
        assertEquals(list.size(),13);


        Goods goods = list.get(0);
        assertNotNull(goods);
        assertEquals(1L, goods.getId());
        assertEquals("又是庸庸碌碌的一天？重整生活的時間管理術",goods.getBookName());
        assertEquals(350,goods.getPrice());
        assertEquals("Swan",goods.getAuthor());
        assertEquals("甘為治",goods.getTranslator());
        assertEquals("楓書坊",goods.getPublisher());
        assertEquals(9789863779216L, goods.getIsbn());
        assertEquals("繁體中文",goods.getLanguage());
        assertEquals("又是庸庸碌碌的一天？.png",goods.getImage());
        assertEquals(7,goods.getBookTypeId());

    }

    @Test
    void findStartEnd()
    {
        List<Goods> list = dao.findStartEnd(0,10);
        assertEquals(list.size(),10);

        Goods goods = list.get(0);
        assertNotNull(goods);
        assertEquals(1L, goods.getId());
        assertEquals("又是庸庸碌碌的一天？重整生活的時間管理術",goods.getBookName());
        assertEquals(350,goods.getPrice());
        assertEquals("Swan",goods.getAuthor());
        assertEquals("甘為治",goods.getTranslator());
        assertEquals("楓書坊",goods.getPublisher());
        assertEquals(9789863779216L, goods.getIsbn());
        assertEquals("繁體中文",goods.getLanguage());
        assertEquals("又是庸庸碌碌的一天？.png",goods.getImage());
        assertEquals(7,goods.getBookTypeId());
    }

    @Test
    void create()
    {
        Goods goods = new Goods();
        goods.setId(999);
        goods.setBookName("命運藏畫中Ⅰ：是意外，還是宿命？");
        goods.setPrice(450);
        goods.setAuthor("中野京子");
        goods.setTranslator("王健波");
        goods.setPublisher("時報出版");
        goods.setIsbn(9786264191302L);
        goods.setLanguage("繁體中文");
        goods.setImage("命運藏畫中.png");
        goods.setBookTypeId(3);
        dao.create(goods);

        Goods goods2= dao.findByPk(999);
        assertNotNull(goods2);
        assertEquals(999, goods2.getId());
        assertEquals("命運藏畫中Ⅰ：是意外，還是宿命？",goods2.getBookName());
        assertEquals(450,goods2.getPrice());
        assertEquals("中野京子",goods2.getAuthor());
        assertEquals("王健波",goods2.getTranslator());
        assertEquals("時報出版",goods2.getPublisher());
        assertEquals(9786264191302L, goods2.getIsbn());
        assertEquals("繁體中文",goods2.getLanguage());
        assertEquals("命運藏畫中.png",goods2.getImage());
        assertEquals(3,goods2.getBookTypeId());

    }

    @Test
    void modify()
    {
        Goods goods = new Goods();
        goods.setId(999);
        goods.setBookName("不務正業的博物館：窺見30件超有戲文物的祕辛");
        goods.setPrice(390);
        goods.setAuthor("郭怡汝");
        goods.setTranslator("");
        goods.setPublisher("如何");
        goods.setIsbn(9789861367309L);
        goods.setLanguage("繁體中文");
        goods.setImage("不務正業的博物館.png");
        goods.setBookTypeId(3);
        dao.modify(goods);

        Goods goods2= dao.findByPk(999);
        assertNotNull(goods2);
        assertEquals(999, goods2.getId());
        assertEquals("不務正業的博物館：窺見30件超有戲文物的祕辛",goods2.getBookName());
        assertEquals(390,goods2.getPrice());
        assertEquals("郭怡汝",goods2.getAuthor());
        assertEquals("",goods2.getTranslator());
        assertEquals("如何",goods2.getPublisher());
        assertEquals(9789861367309L, goods2.getIsbn());
        assertEquals("繁體中文",goods2.getLanguage());
        assertEquals("不務正業的博物館.png",goods2.getImage());
        assertEquals(3,goods2.getBookTypeId());
    }

    @Test
    void remove()
    {
        dao.remove(999);
        Goods goods = dao.findByPk(999);
        assertNull(goods);
    }
}