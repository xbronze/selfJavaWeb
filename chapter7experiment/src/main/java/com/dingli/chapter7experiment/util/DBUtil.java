package com.dingli.chapter7experiment.util;

import com.dingli.chapter7experiment.UserBean;

import java.util.HashMap;

/**
 * @author: xbronze
 * @date: 2024-11-20 09:21
 * @description: TODO
 */
public class DBUtil {

    private static DBUtil instance = new DBUtil();

    public static DBUtil getInstance() {
        return instance;
    }

    // 定义users集合，用于模拟数据库
    private HashMap<String, UserBean> users = new HashMap<String, UserBean>();

    private DBUtil() {
        // 向数据库(users)中存入两条数据
        UserBean user1 = new UserBean();
        user1.setName("Jack");
        user1.setPassword("12345678");
        user1.setEmail("jack@it315.org");
        users.put("Jack ", user1);
        UserBean user2 = new UserBean();
        user2.setName("Rose");
        user2.setPassword("abcdefg");
        user2.setEmail("rose@it315.org");
        users.put("Rose ", user2);
    }

    // 获取数据库(users)中的数据
    public UserBean getUser(String userName) {
        UserBean user = (UserBean) users.get(userName);
        return user;
    }

    // 向数据库(users)插入数据
    public boolean insertUser(UserBean user) {
        if (user == null) {
            return false;
        }
        String userName = user.getName();
        if (users.get(userName) != null) {
            return false;
        }
        users.put(userName, user);
        return true;
    }


}
