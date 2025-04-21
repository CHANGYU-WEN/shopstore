package com.shopstore.store.dao.imp; //實作接口內容



import com.shopstore.JDBCTemplate;
import com.shopstore.PreparedStatementCreator;
import com.shopstore.RowCallbackHandler;
import com.shopstore.store.dao.CustomerDao;
import com.shopstore.store.domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDaoImpJdbc implements CustomerDao
{
    JDBCTemplate jdbcTemplate = new JDBCTemplate();

    private void populate(List<Customer> list , ResultSet rs) throws SQLException
    {
        Customer customer = new Customer();
        customer.setId(rs.getString("id"));
        customer.setName(rs.getString("name"));
        customer.setPassword(rs.getString("password"));
        customer.setAddress(rs.getString("address"));
        customer.setPhone(rs.getString("phone"));
        customer.setBirthday(new Date((rs.getLong("birthday"))));

        list.add(customer);
    }

    @Override
    public Customer findByPk(String pk) {

        List<Customer> list = new ArrayList<Customer>();
        String sql = "select * from customers where id = ?";
        jdbcTemplate.query
                (
                        new PreparedStatementCreator()  // query 方法參數1
                        {
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                //綁定參數
                                ps.setString(1, pk);
                                return ps;
                            }
                        },
                        new RowCallbackHandler()  // query 方法參數2
                        {
                            @Override
                            public void processRow(ResultSet rs) throws SQLException
                            {
                                populate(list, rs);
                            }
                        }
                ); // query 方法括號
        if(list.size() ==1)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Customer> findAll()
    {
        List<Customer> list = new ArrayList<Customer>();
        String sql= "select * from customers ";
        jdbcTemplate.query
                (
                        new PreparedStatementCreator() // 參數1
                        {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                return ps;
                            }
                        },
                        new RowCallbackHandler()  //參數2
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
    public void create(Customer customer)
    {
        String sql="insert into Customers(id,name,password,address,phone,birthday) values(?,?,?,?,?,?)";
        jdbcTemplate.update
                (
                        new PreparedStatementCreator()
                        {
                                @Override
                                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                                {
                                    PreparedStatement ps = con.prepareStatement(sql);
                                    ps.setString(1,customer.getId());
                                    ps.setString(2,customer.getName());
                                    ps.setString(3,customer.getPassword());
                                    ps.setString(4,customer.getAddress());
                                    ps.setString(5,customer.getPhone());
                                    ps.setLong(6,customer.getBirthday().getTime());
                                    return ps;
                                }
                        }
                );

    }

    @Override
    public void modify(Customer customer)
    {
        String sql="update Customers set name =? , password =? , address=?, phone =? , birthday =? where id=?";
        jdbcTemplate.update
                (
                        new PreparedStatementCreator()
                        {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setString(1,customer.getName());
                                ps.setString(2,customer.getPassword());
                                ps.setString(3,customer.getAddress());
                                ps.setString(4,customer.getPhone());
                                ps.setLong(5,customer.getBirthday().getTime());
                                ps.setString(6,customer.getId());
                                return ps;
                            }
                        }
                );
    }


    public void remove(String pk)
    {
        String sql="delete from Customers where id=?";
        jdbcTemplate.update
                (
                        new PreparedStatementCreator()
                        {
                            @Override
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                            {
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setString(1,pk);
                                return ps;
                            }
                        }
                );
    }
}
