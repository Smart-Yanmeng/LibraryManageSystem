package com.york.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.york.dao.IAdminMapper;
import com.york.entity.AdminEntity;
import com.york.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("adminService")
public class IAdminServiceImpl implements IAdminService {

    @Resource
    private IAdminMapper IAdminMapper;

    @Override
    public PageInfo<AdminEntity> queryAdminAll(AdminEntity adminEntity, Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum, limit);
        List<AdminEntity> adminEntityList = IAdminMapper.queryAdminInfoAll(adminEntity);

        return new PageInfo<>(adminEntityList);
    }

    @Override
    public void addAdminSubmit(AdminEntity adminEntity) {
        IAdminMapper.insert(adminEntity);
    }

    @Override
    public AdminEntity queryAdminById(Integer id) {

        return IAdminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateAdminSubmit(AdminEntity adminEntity) {
        IAdminMapper.updateByPrimaryKey(adminEntity);
    }

    @Override
    public void deleteAdminByIds(List<String> ids) {
        for (String id : ids) {
            IAdminMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }

    @Override
    public AdminEntity queryUserByNameAndPassword(String username, String password) {

        return IAdminMapper.queryUserByNameAndPassword(username, password);
    }
}
