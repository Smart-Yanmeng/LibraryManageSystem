package com.york.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {

    // 用户 ID
    private Integer id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 管理员类型
    private Integer adminType;

    private static final long serialVersionUID = 1L;

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}
