package com.york.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.york.dao.ITypeInfoMapper;
import com.york.entity.TypeInfo;
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
    public PageInfo<TypeInfo> queryTypeInfoAll(String name, Integer pageNum, Integer limit) {

        PageHelper.startPage(pageNum, limit);
        List<TypeInfo> typeInfoList = ITypeInfoMapper.queryTypeInfoAll(name);

        return new PageInfo<>(typeInfoList);
    }

    @Override
    public void addTypeSubmit(TypeInfo info) {

        ITypeInfoMapper.addTypeSubmit(info);
    }

    @Override
    public TypeInfo queryTypeInfoById(Integer id) {

        return ITypeInfoMapper.queryTypeInfoById(id);
    }

    @Override
    public void updateTypeSubmit(TypeInfo info) {

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
