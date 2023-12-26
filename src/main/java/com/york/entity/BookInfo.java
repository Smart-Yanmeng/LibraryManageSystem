package com.york.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BookInfo implements Serializable {

    // 图书 ID
    private Integer id;

    // 图书名称
    private String name;

    // 作者
    private String author;

    // 出版社
    private String publish;

    // ISBN
    private String isbn;

    // 简介
    private String introduction;

    // 语言
    private String language;

    // 价格
    private Double price;

    // 出版日期
    private Date publishDate;

    // 图书类型 ID
    private Integer typeId;

    // 出借状态
    private Integer status;

    // 图书信息
    private TypeInfo typeInfo;

    // 图书数量
    private Integer counts;

    private static final long serialVersionUID = 1L;

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }
}
