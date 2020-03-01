package org.csu.backtopetstore.persistence.impl;

import java.sql.*;

//相当于数据库操作驱动，把所有操作都放在这里使用
public class DBUtil {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/backtopetstore?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "root";

    public static Connection getConnection() throws  Exception{
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            throw e;
        }
        return connection;
    }

    public static void closeConnection(Connection connection) throws  Exception{
        if(connection != null){
            connection.close();
        }
    }

    public static void closeStatement(Statement statement) throws Exception{
        if(statement != null){
            statement.close();
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws Exception{
        if(preparedStatement != null){
            preparedStatement.close();;
        }
    }

    public static void closeResultSet(ResultSet resultSet)throws Exception{
        if(resultSet != null){
            resultSet.close();
        }
    }
/*
//测试连接
    public static void main(String[] args)throws Exception{
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);
    }*/
}
