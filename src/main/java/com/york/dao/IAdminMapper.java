package com.york.dao;

import com.york.entity.AdminEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAdminMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(AdminEntity record);

    int insertSelective(AdminEntity record);

    AdminEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminEntity record);

    int updateByPrimaryKey(AdminEntity record);

    /**
     * 管理员查询
     */
    List<AdminEntity> queryAdminInfoAll(AdminEntity adminEntity);

    /**
     * 根据用户名和密码查询用户信息
     */
    AdminEntity queryUserByNameAndPassword(@Param("username") String username, @Param("password") String password);
}
