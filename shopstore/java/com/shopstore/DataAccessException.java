package com.shopstore; //封裝資料存取錯誤

public class DataAccessException extends RuntimeException
{
    public DataAccessException(String message)
    {
        super(message);
    }
    public DataAccessException(String message, Throwable ex)
    {
        super(message,ex);
    }
}
