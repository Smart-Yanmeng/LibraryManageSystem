package com.york.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.york.dao.ITypeInfoMapper;
import com.york.entity.TypeInfoEntity;
import com.york.service.ITypeInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("typeInfoService")
public class ITypeInfoServiceImpl implements ITypeInfoService {

    @Resource
    private ITypeInfoMapper ITypeInfoMapper;

    @Override
    public PageInfo<TypeInfoEntity> queryTypeInfoAll(String name, Integer pageNum, Integer limit) {

        PageHelper.startPage(pageNum, limit);
        List<TypeInfoEntity> typeInfoEntityList = ITypeInfoMapper.queryTypeInfoAll(name);

        return new PageInfo<>(typeInfoEntityList);
    }

    @Override
    public void addTypeSubmit(TypeInfoEntity info) {

        ITypeInfoMapper.addTypeSubmit(info);
    }

    @Override
    public TypeInfoEntity queryTypeInfoById(Integer id) {

        return ITypeInfoMapper.queryTypeInfoById(id);
    }

    @Override
    public void updateTypeSubmit(TypeInfoEntity info) {

        ITypeInfoMapper.updateTypeSubmit(info);
    }

    @Override
    public void deleteTypeByIds(List<String> id) {

        List<Integer> list = new ArrayList<>();
        for (String cid : id) {
            int id2 = Integer.parseInt(cid);

            list.add(id2);
        }

        ITypeInfoMapper.deleteTypeByIds(list);
    }
}
