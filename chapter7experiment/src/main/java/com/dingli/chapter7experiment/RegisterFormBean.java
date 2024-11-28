package com.dingli.chapter7experiment;

import com.dingli.chapter7experiment.util.DBUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xbronze
 * @date: 2024-11-20 09:18
 * @description: TODO
 */
public class RegisterFormBean {

    private String name;            //定义用户名
    private String password;     //定义密码
    private String password2;   //定义确认密码
    private String email;           //定义邮箱


    private Map<String, String> errors = new HashMap<String, String>();

    public boolean validate() {
        boolean flag = true;
        if (DBUtil.getInstance().getUser(name) != null) {
            errors.put("name", "用户名已存在");
            flag = false;
        }
        // 比较两次输入的密码是否一样
        if (!password.equals(password2)) {
            errors.put("password2", "两次输入的密码不匹配");
            flag = false;
        }
        // 此处只展示了对email格式的校验采用了正则表达式，其他省略
        if (email == null || email.trim().equals("")) {
            errors.put("email", "请输入邮箱");
            flag = false;
        } else if ((!email.matches("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+"))) {
            errors.put("email", "邮箱格式错误.");
            flag = false;
        }
        return flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }


}
