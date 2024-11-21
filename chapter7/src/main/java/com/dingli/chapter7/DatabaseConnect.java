package com.dingli.chapter7;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author: xbronze
 * @date: 2024-11-21 11:32
 * @description: TODO
 */
public class DatabaseConnect {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/db_spring";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";

    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();

    public static Connection rebuildConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection() {
        // 获取当前线程中的连接对象
        Connection conn = THREAD_LOCAL.get();
        if (conn == null) {
            conn = rebuildConnection();
            THREAD_LOCAL.set(conn);
        }
        return conn;
    }

    // 关闭数据库连接
    public static void closeConnection() {
        // 获取当前线程中的连接对象
        Connection conn = THREAD_LOCAL.get();
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            THREAD_LOCAL.remove();
        }
    }

}
