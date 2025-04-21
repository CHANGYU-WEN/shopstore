package com.shopstore.store.dao.imp;

import com.shopstore.JDBCTemplate;
import com.shopstore.PreparedStatementCreator;
import com.shopstore.RowCallbackHandler;
import com.shopstore.store.dao.OrderDao;
import com.shopstore.store.domain.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpJdbc implements OrderDao
{
    JDBCTemplate jdbcTemplate = new JDBCTemplate();

    private void populate(List<Order>list , ResultSet rs) throws SQLException
    {
        Order orders = new Order();
        orders.setId(rs.getString("id"));
        orders.setOrderDate(new Date(rs.getLong("order_date")));
        orders.setStatus(rs.getInt("status"));
        orders.setTotal(rs.getDouble("total"));
        list.add(orders);
    }

    @Override
    public Order findByPk(String pk)
    {
        List<Order> list = new ArrayList<Order>();
        String sql = "select id,order_date , status , total from orders where id = ?";
        jdbcTemplate.query
                (
                        new PreparedStatementCreator()
                        {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setString(1, pk);
                                return ps;
                            }
                        },
                        new RowCallbackHandler()
                        {
                            @Override
                            public void processRow(ResultSet rs) throws SQLException
                            {
                                populate( list,rs);
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
    public List<Order> findAllOrders()
    {
        List<Order> list = new ArrayList<Order>();
        String sql = "select id, order_date , status , total from orders";
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
                                populate( list,rs);
                            }
                        }
                );
        return list;
    }

    @Override
    public void create(Order order)
    {
        String sql ="insert into orders (id, order_date, status, total ) values (?,?,?,?);";
        jdbcTemplate.update
                (
                        new PreparedStatementCreator() {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setString(1, order.getId());
                                ps.setLong(2,order.getOrderDate().getTime());
                                ps.setInt(3,order.getStatus());
                                ps.setDouble(4,order.getTotal());
                                return ps;
                            }
                        }
                ); // update方法的括號
    }

    @Override
    public void modify(Order order)
    {
        String sql ="update orders set order_date=? ,status = ? , total = ? where id = ?;";
        jdbcTemplate.update
                (
                        new PreparedStatementCreator() {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setLong(1,order.getOrderDate().getTime());
                                ps.setInt(2,order.getStatus());
                                ps.setDouble(3,order.getTotal());
                                ps.setString(4, order.getId());
                                return ps;
                            }
                        }
                ); // update方法的括號

    }

    @Override
    public void remove(String pk)
    {
        String sql ="delete from orders where id = ?;";
        jdbcTemplate.update
                (
                        new PreparedStatementCreator() {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setString(1, pk);
                                return ps;
                            }
                        }
                ); // update方法的括號
    }
}
