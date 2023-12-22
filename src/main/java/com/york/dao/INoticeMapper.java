package com.york.dao;

import com.york.entity.NoticeEntity;

import java.util.List;

public interface INoticeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(NoticeEntity record);

    int insertSelective(NoticeEntity record);

    NoticeEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoticeEntity record);

    int updateByPrimaryKey(NoticeEntity record);

    /**
     * 查询所有公告信息
     */
    List<NoticeEntity> queryNoticeAll(NoticeEntity noticeEntity);
}
