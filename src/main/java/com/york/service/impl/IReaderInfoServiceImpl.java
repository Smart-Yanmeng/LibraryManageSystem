package com.york.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.york.dao.IReaderInfoMapper;
import com.york.entity.ReaderInfoEntity;
import com.york.service.IReaderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("readerInfoService")
public class IReaderInfoServiceImpl implements IReaderInfoService {

    @Resource
    private IReaderInfoMapper IReaderInfoMapper;

    @Override
    public PageInfo<ReaderInfoEntity> queryAllReaderInfo(ReaderInfoEntity readerInfoEntity, Integer pageNum, Integer limit) {

        PageHelper.startPage(pageNum, limit);
        List<ReaderInfoEntity> readerInfoEntityList = IReaderInfoMapper.queryAllReaderInfo(readerInfoEntity);

        return new PageInfo<>(readerInfoEntityList);
    }

    @Override
    public void addReaderInfoSubmit(ReaderInfoEntity readerInfoEntity) {

        IReaderInfoMapper.insert(readerInfoEntity);
    }

    @Override
    public ReaderInfoEntity queryReaderInfoById(Integer id) {

        return IReaderInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateReaderInfoSubmit(ReaderInfoEntity readerInfoEntity) {

        IReaderInfoMapper.updateByPrimaryKey(readerInfoEntity);
    }

    @Override
    public void deleteReaderInfoByIds(List<String> ids) {

        for (String id : ids) {
            IReaderInfoMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }

    @Override
    public ReaderInfoEntity queryUserInfoByNameAndPassword(String username, String password) {

        return IReaderInfoMapper.queryUserInfoByNameAndPassword(username, password);
    }
}
