package com.york.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.york.dao.IBookInfoMapper;
import com.york.entity.BookInfo;
import com.york.service.IBookInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("bookInfoService")
public class IBookInfoServiceImpl implements IBookInfoService {

    @Resource
    private IBookInfoMapper IBookInfoMapper;

    @Override
    public PageInfo<BookInfo> queryBookInfoAll(BookInfo bookInfo, Integer pageNum, Integer limit) {

        PageHelper.startPage(pageNum, limit);
        List<BookInfo> bookInfoList = IBookInfoMapper.queryBookInfoAll(bookInfo);

        return new PageInfo<>(bookInfoList);
    }

    @Override
    public void addBookSubmit(BookInfo bookInfo) {
        IBookInfoMapper.insert(bookInfo);
    }

    @Override
    public BookInfo queryBookInfoById(Integer id) {

        return IBookInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateBookSubmit(BookInfo info) {
        IBookInfoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public void deleteBookByIds(List<String> ids) {
        for (String id : ids) {
            IBookInfoMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }

    @Override
    public List<BookInfo> getBookCountByType() {

        return IBookInfoMapper.getBookCountByType();
    }
}
