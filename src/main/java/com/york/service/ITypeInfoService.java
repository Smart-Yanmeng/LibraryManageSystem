package com.york.service;

import com.github.pagehelper.PageInfo;
import com.york.entity.TypeInfoEntity;

import java.util.List;

/**
 * 图书类型业务接口
 */
public interface ITypeInfoService {
    /**
     * 查询所有记录
     */
    PageInfo<TypeInfoEntity> queryTypeInfoAll(String name, Integer pageNum, Integer limit);

    /**
     * 添加图书类型
     */
    void addTypeSubmit(TypeInfoEntity info);

    /**
     * 修改 根据id查询记录信息
     */
    TypeInfoEntity queryTypeInfoById(Integer id);

    /**
     * 修改提交
     */
    void updateTypeSubmit(TypeInfoEntity info);

    /**
     * 根据ids删除记录信息
     */
    void deleteTypeByIds(List<String> id);
}
