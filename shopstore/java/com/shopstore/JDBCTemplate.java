package com.shopstore;

import java.sql.*;


public class JDBCTemplate
{
    public void query
            (PreparedStatementCreator pscreator , RowCallbackHandler callbackHandler)
            throws DataAccessException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;

        try
        {
            connection = DBHelp.getConnection();
            preparedStatement=pscreator.createPreparedStatement(connection);
            resultSet =preparedStatement.executeQuery();

            while(resultSet.next())
            {
                callbackHandler.processRow(resultSet);
            }

        }
        catch(SQLException e)
        {
            throw new DataAccessException("JdbcTemplate 中的SQLException",e);
        }
        catch(ClassNotFoundException e)
        {
            throw new DataAccessException("JdbcTemplate 中的ClassNotFoundException",e);
        }
        finally
        {
            if(connection!=null)
            {
                try{connection.close();}
                catch(SQLException e){throw new DataAccessException("JdbcTemplate中沒辦法關閉資料庫連接",e);}
            }
            if(preparedStatement!=null)
            {
                try{preparedStatement.close();}
                catch(SQLException e){throw new DataAccessException("JdbcTemplate中沒辦法關閉SQL查詢語句",e);}
            }
            if(resultSet!=null)
            {
                try{resultSet.close();}
                catch(SQLException e){throw new DataAccessException("JdbcTemplate中沒辦法關閉回傳結果",e);}
            }
        } //finally的括號
    } // query方法的括號

    public void update(PreparedStatementCreator pscreator) throws DataAccessException
    {
        Connection connection =null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = DBHelp.getConnection();
            preparedStatement = pscreator.createPreparedStatement(connection);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new DataAccessException("JdbcTemplate中的SQLException",e);
        }
        catch(ClassNotFoundException e)
        {
            throw new DataAccessException("JdbcTemplate中的ClassNotFoundException",e);
        }
        finally
        {
            if(connection!=null)
            {
                try{connection.close();}
                catch(SQLException e){throw new DataAccessException("JdbcTemplate 中沒辦法關閉資料庫連接",e);}
            }
            if(preparedStatement!=null)
            {
                try{preparedStatement.close();}
                catch(SQLException e){throw new DataAccessException("JdbcTemplate 中沒辦法關閉 SQL語句",e);}
            }
        }// finally 的括號
    }//update方法的括號

}
