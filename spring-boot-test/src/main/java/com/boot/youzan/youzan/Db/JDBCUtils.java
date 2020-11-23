package com.boot.youzan.youzan.Db;

import java.sql.ResultSet;
import java.sql.SQLException;


import java.io.InputStream;
import java.sql.*;
import java.util.Properties;



import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by dell on 2019/5/22.
 */
public class JDBCUtils {

    private static String url="jdbc:mysql://172.16.6.5:3306/fcoc-member?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
    private static String username="hx_ljq";
    private static String password="7357Lijiaqi7234";
    private static String driver="com.mysql.jdbc.Driver";
    //  //静态代码块加载配置文件信息
//  static {
//    ResourceBundle db = ResourceBundle.getBundle("db");
//    driver = db.getString("driver");
//    url = db.getString("url");
//    username = db.getString("username");
//    password = db.getString("password");
//  }
    //静态代码块加载配置文件信息
//    static {
//        try {
//            //获取类加载器
//            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
//            //通过类加载器的方法获取一个输入流
//            InputStream resourceAsStream = classLoader.getResourceAsStream("db.properties");
//            Properties properties = new Properties();
//            properties.load(resourceAsStream);
//            //获取相关参数的值
//            driver = properties.getProperty("driver");
//            url = properties.getProperty("url");
//            username = properties.getProperty("username");
//            password = properties.getProperty("password");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConnection() {

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     * @param conn
     * @param pstmt
     * @param rs
     */
    public static void relase(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}