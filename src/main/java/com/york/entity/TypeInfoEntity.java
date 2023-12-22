package com.york.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TypeInfoEntity implements Serializable {

    // 图书类型 ID
    private Integer id;

    // 图书类型名称
    private String name;

    // 图书类型描述
    private String remarks;

    private static final long serialVersionUID = 1L;

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
