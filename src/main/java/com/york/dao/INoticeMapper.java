package com.york.dao;

import com.york.entity.Notice;

import java.util.List;

public interface INoticeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    /**
     * 查询所有公告信息
     */
    List<Notice> queryNoticeAll(Notice notice);

}
