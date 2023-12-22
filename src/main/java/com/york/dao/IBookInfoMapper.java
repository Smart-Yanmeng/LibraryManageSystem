package com.york.dao;

import com.york.entity.BookInfoEntity;

import java.util.List;

public interface IBookInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(BookInfoEntity record);

    int insertSelective(BookInfoEntity record);

    BookInfoEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookInfoEntity record);

    int updateByPrimaryKey(BookInfoEntity record);

    /**
     * 查询所有图书记录
     */
    List<BookInfoEntity> queryBookInfoAll(BookInfoEntity bookInfoEntity);

    /**
     * 根据类型获取图书数量
     */
    List<BookInfoEntity> getBookCountByType();
}
