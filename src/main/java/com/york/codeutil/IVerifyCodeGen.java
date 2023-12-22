package com.york.codeutil;

import com.york.entity.VerifyCodeEntity;

import java.io.IOException;
import java.io.OutputStream;

public interface IVerifyCodeGen {

    /**
     * 生成验证码并返回code，将图片写的os中
     *
     * @param width  图片宽度
     * @param height 图片高度
     * @param os     输出流
     * @return 验证码
     * @throws IOException IO异常
     */
    String generate(int width, int height, OutputStream os) throws IOException;

    /**
     * 生成验证码对象
     *
     * @param width  图片宽度
     * @param height 图片高度
     * @return 验证码对象
     * @throws IOException IO异常
     */
    VerifyCodeEntity generate(int width, int height) throws IOException;
}
