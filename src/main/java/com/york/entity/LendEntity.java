package com.york.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class LendEntity implements Serializable {

    // 借阅记录 ID
    private Integer id;

    // 图书 ID
    private Integer bookId;

    // 读者 ID
    private Integer readerId;

    // 接受日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lendDate;

    // 归还日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date backDate;

    // 是否归还
    private Integer backType;

    // 是否续借
    private String exceptRemarks;

    // 图书信息
    private BookInfoEntity bookInfoEntity;

    // 读者信息
    private ReaderInfoEntity readerInfoEntity;

    private static final long serialVersionUID = 1L;
}
