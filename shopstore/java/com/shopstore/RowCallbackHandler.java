package com.shopstore; //資料庫回傳的介面

import java.sql.*;

public interface RowCallbackHandler
{
    void processRow(ResultSet rs) throws SQLException;
}
