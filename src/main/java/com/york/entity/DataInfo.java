package com.york.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 返回状态码
    private Integer code = 200;

    // 返回消息
    private String msg = "Success";

    // 数据
    private Object data;

    // 数据总数
    private Long count;

    public DataInfo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public DataInfo(Integer code, String msg, Object data, Long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    public static DataInfo ok() {
        return new DataInfo(Constants.OK_CODE, Constants.OK_MSG, null);
    }

    public static DataInfo ok(Object data) {
        return new DataInfo(Constants.OK_CODE, Constants.OK_MSG, data);
    }

    public static DataInfo ok(String msg, long count, Object data) {
        return new DataInfo(Constants.OK_CODE, Constants.OK_MSG, data, count);
    }

    public static DataInfo ok(String msg, Object data) {
        return new DataInfo(Constants.OK_CODE, msg, data);
    }

    public static DataInfo fail(String msg) {
        return new DataInfo(Constants.FAIL_CODE, msg, null);
    }

    public static DataInfo fail(int errorCode, String msg) {
        return new DataInfo(errorCode, msg, null);
    }
}
