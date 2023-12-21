package com.york.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.york.dao.IReaderInfoMapper;
import com.york.entity.ReaderInfo;
import com.york.service.IReaderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("readerInfoService")
public class IReaderInfoServiceImpl implements IReaderInfoService {

    @Resource
    private IReaderInfoMapper IReaderInfoMapper;

    @Override
    public PageInfo<ReaderInfo> queryAllReaderInfo(ReaderInfo readerInfo, Integer pageNum, Integer limit) {

        PageHelper.startPage(pageNum, limit);
        List<ReaderInfo> readerInfoList = IReaderInfoMapper.queryAllReaderInfo(readerInfo);

        return new PageInfo<>(readerInfoList);
    }

    @Override
    public void addReaderInfoSubmit(ReaderInfo readerInfo) {

        IReaderInfoMapper.insert(readerInfo);
    }

    @Override
    public ReaderInfo queryReaderInfoById(Integer id) {

        return IReaderInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateReaderInfoSubmit(ReaderInfo readerInfo) {

        IReaderInfoMapper.updateByPrimaryKey(readerInfo);
    }

    @Override
    public void deleteReaderInfoByIds(List<String> ids) {

        for (String id : ids) {
            IReaderInfoMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }

    @Override
    public ReaderInfo queryUserInfoByNameAndPassword(String username, String password) {

        return IReaderInfoMapper.queryUserInfoByNameAndPassword(username, password);
    }
}
