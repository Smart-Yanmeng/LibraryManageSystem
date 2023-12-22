package com.york.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.york.dao.IBookInfoMapper;
import com.york.entity.BookInfoEntity;
import com.york.service.IBookInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("bookInfoService")
public class IBookInfoServiceImpl implements IBookInfoService {

    @Resource
    private IBookInfoMapper IBookInfoMapper;

    @Override
    public PageInfo<BookInfoEntity> queryBookInfoAll(BookInfoEntity bookInfoEntity, Integer pageNum, Integer limit) {

        PageHelper.startPage(pageNum, limit);
        List<BookInfoEntity> bookInfoEntityList = IBookInfoMapper.queryBookInfoAll(bookInfoEntity);

        return new PageInfo<>(bookInfoEntityList);
    }

    @Override
    public void addBookSubmit(BookInfoEntity bookInfoEntity) {
        IBookInfoMapper.insert(bookInfoEntity);
    }

    @Override
    public BookInfoEntity queryBookInfoById(Integer id) {

        return IBookInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateBookSubmit(BookInfoEntity info) {
        IBookInfoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public void deleteBookByIds(List<String> ids) {
        for (String id : ids) {
            IBookInfoMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }

    @Override
    public List<BookInfoEntity> getBookCountByType() {

        return IBookInfoMapper.getBookCountByType();
    }
}
