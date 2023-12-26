package com.york.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReaderInfo implements Serializable {

    // 用户 ID
    private Integer id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 真实姓名
    private String realName;

    // 性别
    private String sex;

    // 出生日期
    private Date birthday;

    // 地址
    private String address;

    // 电话
    private String tel;

    // 邮箱
    private String email;

    // 注册日期
    private Date registerDate;

    private String readerNumber;

    private static final long serialVersionUID = 1L;

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public void setReaderNumber(String readerNumber) {
        this.readerNumber = readerNumber == null ? null : readerNumber.trim();
    }
}
