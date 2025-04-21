package com.shopstore.store.dao.imp;

import com.shopstore.JDBCTemplate;
import com.shopstore.PreparedStatementCreator;
import com.shopstore.RowCallbackHandler;
import com.shopstore.store.dao.OrderLineItemDao;
import com.shopstore.store.domain.Goods;
import com.shopstore.store.domain.Order;
import com.shopstore.store.domain.OrderLineItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineItemDaoImpJdbc implements OrderLineItemDao
{
    JDBCTemplate jdbcTemplate = new JDBCTemplate();

    private void populate(List<OrderLineItem>list, ResultSet rs) throws SQLException
    {
        OrderLineItem item = new OrderLineItem();
        item.setId(rs.getLong("id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setSubtotal(rs.getDouble("sub_total"));


        Order order = new Order();
        order.setId(rs.getString("orderid"));
        item.setOrders(order);

        Goods goods = new Goods();
        goods.setId(rs.getLong("goodsid"));
        item.setGoods(goods);

        list.add(item);

    }

    @Override
    public OrderLineItem findByPk(long pk)
    {
        List<OrderLineItem> list = new ArrayList<OrderLineItem>();
        String sql ="select id,goodsid,orderid,quantity,sub_total from orderlineitems where id =?;";

        jdbcTemplate.query
                (
                        new PreparedStatementCreator() {
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
                );
        if(list.size()==1)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<OrderLineItem> findAll()
    {
        List<OrderLineItem> list = new ArrayList<OrderLineItem>();
        String sql="select id,goodsid,orderid,quantity,sub_total from orderlineitems ;";
        jdbcTemplate.query
                (
                        new PreparedStatementCreator() {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
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
    public void create(OrderLineItem lineItem)
    {
        String sql ="insert into orderlineitems (id,goodsid,orderid,quantity,sub_total)values(?,?,?,?,?);";
        jdbcTemplate.update
                (
                        new PreparedStatementCreator()
                        {
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setLong(1, lineItem.getId());
                                ps.setLong(2,lineItem.getGoods().getId());
                                ps.setString(3,lineItem.getOrders().getId());
                                ps.setInt(4,lineItem.getQuantity());
                                ps.setDouble(5,lineItem.getSubtotal());
                                return ps;
                            }
                        }
                );

    }

    @Override
    public void modify(OrderLineItem lineItem)
    {
        String sql ="update orderlineitems set goodsid=?, orderid=?,quantity=?,sub_total=? where id=?;";
        jdbcTemplate.update
                (
                        new PreparedStatementCreator()
                        {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setLong(1,lineItem.getGoods().getId());
                                ps.setString(2,lineItem.getOrders().getId());
                                ps.setInt(3,lineItem.getQuantity());
                                ps.setDouble(4,lineItem.getSubtotal());
                                ps.setLong(5, lineItem.getId());
                                return ps;
                            }
                        }
                );
    }

    @Override
    public void remove(int pk)
    {
        String sql ="delete from orderlineitems where id=?;";
        jdbcTemplate.update
                (
                        new PreparedStatementCreator()
                        {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setInt(1, pk);
                                return ps;
                            }
                        }
                );

    }
}
