package com.shopstore; //連接資料庫介面

import java.sql.*;


public interface PreparedStatementCreator
{
    PreparedStatement createPreparedStatement(Connection con)throws SQLException;
}
