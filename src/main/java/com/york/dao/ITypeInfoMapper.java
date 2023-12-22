package com.york.dao;

import com.york.entity.TypeInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITypeInfoMapper {
    /**
     * 查询所有的记录信息
     */
    List<TypeInfoEntity> queryTypeInfoAll(@Param(value = "name") String name);

    /**
     * 添加图书类型
     */
    void addTypeSubmit(TypeInfoEntity info);

    /**
     * 修改 根据id查询记录信息，查询弹出修改界，然后修改进行确认提交
     */
    TypeInfoEntity queryTypeInfoById(Integer id);

    /**
     * 修改提交
     */
    void updateTypeSubmit(TypeInfoEntity info);

    /**
     * 根据ids删除记录信息
     */
    void deleteTypeByIds(List<Integer> id);
}
