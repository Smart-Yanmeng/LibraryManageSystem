package com.york.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.york.dao.IAdminMapper;
import com.york.entity.Admin;
import com.york.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("adminService")
public class IAdminServiceImpl implements IAdminService {

    @Resource
    private IAdminMapper IAdminMapper;

    @Override
    public PageInfo<Admin> queryAdminAll(Admin admin, Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum, limit);
        List<Admin> adminList = IAdminMapper.queryAdminInfoAll(admin);

        return new PageInfo<>(adminList);
    }

    @Override
    public void addAdminSubmit(Admin admin) {
        IAdminMapper.insert(admin);
    }

    @Override
    public Admin queryAdminById(Integer id) {

        return IAdminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateAdminSubmit(Admin admin) {
        IAdminMapper.updateByPrimaryKey(admin);
    }

    @Override
    public void deleteAdminByIds(List<String> ids) {
        for (String id : ids) {
            IAdminMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }

    @Override
    public Admin queryUserByNameAndPassword(String username, String password) {

        return IAdminMapper.queryUserByNameAndPassword(username, password);
    }
}
