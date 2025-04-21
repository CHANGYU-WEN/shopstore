package com.shopstore.store.dao.imp;

import com.shopstore.JDBCTemplate;
import com.shopstore.PreparedStatementCreator;
import com.shopstore.RowCallbackHandler;
import com.shopstore.store.dao.GoodsDao;
import com.shopstore.store.domain.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuffer;



public class GoodsDaoImpJdbc implements GoodsDao
{
    JDBCTemplate jdbcTemplate= new JDBCTemplate();

    private void populate(List<Goods> list , ResultSet rs) throws SQLException
    {
        Goods goods = new Goods();
        goods.setId(rs.getLong("id"));
        goods.setBookName(rs.getString("bookName"));
        goods.setPrice(rs.getDouble("price"));
        goods.setAuthor(rs.getString("author"));
        goods.setTranslator(rs.getString("translator"));
        goods.setPublisher(rs.getString("publisher"));
        goods.setIsbn(rs.getLong("isbn"));
        goods.setLanguage(rs.getString("language"));
        goods.setImage(rs.getString("image"));
        goods.setBookTypeId(rs.getInt("bookTypeId"));

        list.add(goods);
    }

    @Override
    public Goods findByPk(long pk)
    {
        List<Goods> list = new ArrayList<Goods>();
        String sql="select id, bookName, price, author,translator, publisher ,isbn , language, image, bookTypeId from goods where id = ?";
        jdbcTemplate.query
                (
                        new PreparedStatementCreator()
                        {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setLong(1, pk);
                                return ps;
                            }
                        },
                        new RowCallbackHandler()
                        {
                            @Override
                            public void processRow(ResultSet rs) throws SQLException
                            {
                                populate(list, rs);
                            }
                        }
                ); // query 方法的刮號
                if(list.size()==1)
                {
                    return list.get(0);
                }
                return null;
    }

    @Override
    public List<Goods> findAllGoods()
    {
        List<Goods> list = new ArrayList<Goods>();
        String sql="select id, bookName, price, author,translator, publisher ,isbn , language, image, bookTypeId from goods";

        jdbcTemplate.query
                (
                        new PreparedStatementCreator() {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                return ps;
                            }
                        },
                        new RowCallbackHandler()
                        {
                            @Override
                            public void processRow(ResultSet rs) throws SQLException
                            {
                                populate(list, rs);
                            }
                        }
                );
        return list;
    }

    @Override
    public List<Goods> findStartEnd(int start, int end)
    {
        List<Goods> list = new ArrayList<Goods>();
        StringBuffer sql = new StringBuffer("select id, bookName, price, author,translator, publisher ,isbn , language, image, bookTypeId from goods ");
        sql.append("LIMIT ").append(end-start);
        sql.append(" OFFSET ").append(start);

        jdbcTemplate.query
                (
                        new PreparedStatementCreator() {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql.toString());
                                return ps;
                            }
                        },
                        new RowCallbackHandler()
                        {
                            @Override
                            public void processRow(ResultSet rs) throws SQLException
                            {
                                populate(list, rs);
                            }
                        }
                ); //query的括號
        return list;
    }

    @Override
    public void create(Goods goods)
    {
        String sql = "insert into goods(id,bookName,price,author,translator,publisher,isbn,language, image,bookTypeId)values(?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update
                (
                        new PreparedStatementCreator()
                        {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setLong(1, goods.getId());
                                ps.setString(2, goods.getBookName());
                                ps.setDouble(3, goods.getPrice());
                                ps.setString(4, goods.getAuthor());
                                ps.setString(5, goods.getTranslator());
                                ps.setString(6, goods.getPublisher());
                                ps.setLong(7, goods.getIsbn());
                                ps.setString(8, goods.getLanguage());
                                ps.setString(9, goods.getImage());
                                ps.setInt(10, goods.getBookTypeId());
                                return ps;
                            }
                        }
                ); // update方法

    }

    @Override
    public void modify(Goods goods)
    {
        String sql ="update goods set bookName=? , price=? , author=?, translator =? ,publisher=?, isbn=?, language=?, image=?,bookTypeId=? where id=?  ";
        jdbcTemplate.update
                (
                        new PreparedStatementCreator()
                        {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setString(1, goods.getBookName());
                                ps.setDouble(2, goods.getPrice());
                                ps.setString(3,goods.getAuthor());
                                ps.setString(4, goods.getTranslator());
                                ps.setString(5, goods.getPublisher());
                                ps.setLong(6, goods.getIsbn());
                                ps.setString(7, goods.getLanguage());
                                ps.setString(8, goods.getImage());
                                ps.setInt(9, goods.getBookTypeId());
                                ps.setLong(10, goods.getId());
                                return ps;
                            }
                        }
                ); // update 方法的括號

    }

    @Override
    public void remove(long pk)
    {
        String sql = "delete from goods where id=?";
        jdbcTemplate.update
                (
                        new PreparedStatementCreator() {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setLong(1, pk);
                                return ps;
                            }
                        }
                ); // update的括號
    }
}
