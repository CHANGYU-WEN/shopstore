package com.shopstore;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.*;

public class DBHelp
{
    static Properties info = new Properties();

    //初始化
    static
    {
        //用inputStream讀取檔案
        InputStream in = DBHelp.class.getClassLoader().getResourceAsStream("config.properties");
        try
        {
            info.load(in);
        }
        catch(IOException e) {
            e.printStackTrace(); //錯誤追蹤
        }
    }

    public static Connection getConnection() throws SQLException,ClassNotFoundException
    {
        Class.forName(info.getProperty("driver"));
        Connection connection = DriverManager.getConnection(info.getProperty("url"),info);
        return connection;
    }
}
