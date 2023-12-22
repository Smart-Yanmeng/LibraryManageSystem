package com.york.service;

import com.github.pagehelper.PageInfo;
import com.york.entity.AdminEntity;

import java.util.List;

/**
 * 管理员业务接口
 */
public interface IAdminService {

    /**
     * 查询所有管理员（分页）
     */
    PageInfo<AdminEntity> queryAdminAll(AdminEntity adminEntity, Integer pageNum, Integer limit);

    /**
     * 添加提交
     */
    void addAdminSubmit(AdminEntity adminEntity);

    /**
     * 根据id查询（修改）
     */
    AdminEntity queryAdminById(Integer id);

    /**
     * 修改提交
     */
    void updateAdminSubmit(AdminEntity adminEntity);

    /**
     * 删除
     */
    void deleteAdminByIds(List<String> ids);

    /**
     * 根据用户名和密码查询用户信息
     */
    AdminEntity queryUserByNameAndPassword(String username, String password);
}
