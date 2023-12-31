package com.york.service;

import com.github.pagehelper.PageInfo;
import com.york.entity.TypeInfo;

import java.util.List;

/**
 * 图书类型业务接口
 */
public interface ITypeInfoService {
    /**
     * 查询所有记录
     */
    PageInfo<TypeInfo> queryTypeInfoAll(String name, Integer pageNum, Integer limit);

    /**
     * 添加图书类型
     */
    void addTypeSubmit(TypeInfo info);

    /**
     * 修改 根据id查询记录信息
     */
    TypeInfo queryTypeInfoById(Integer id);

    /**
     * 修改提交
     */
    void updateTypeSubmit(TypeInfo info);

    /**
     * 根据ids删除记录信息
     */
    void deleteTypeByIds(List<String> id);
}
