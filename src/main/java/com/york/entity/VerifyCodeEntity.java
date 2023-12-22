package com.york.entity;

import lombok.Data;

@Data
public class VerifyCodeEntity {

    // 验证码
    private String code;

    // 验证码图片
    private byte[] imgBytes;

    // 过期时间
    private long expireTime;
}
