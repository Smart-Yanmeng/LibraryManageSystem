package com.york.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.york.dao.INoticeMapper;
import com.york.entity.NoticeEntity;
import com.york.service.INoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("noticeService")
public class INoticeServiceImpl implements INoticeService {

    @Resource
    private INoticeMapper INoticeMapper;

    @Override
    public PageInfo<NoticeEntity> queryAllNotice(NoticeEntity noticeEntity, Integer pageNum, Integer limit) {

        PageHelper.startPage(pageNum, limit);
        List<NoticeEntity> noticeEntityList = INoticeMapper.queryNoticeAll(noticeEntity);

        return new PageInfo<>(noticeEntityList);
    }

    @Override
    public void addNotice(NoticeEntity noticeEntity) {
        INoticeMapper.insert(noticeEntity);
    }

    @Override
    public NoticeEntity queryNoticeById(Integer id) {

        return INoticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteNoticeByIds(List<String> ids) {

        for (String id : ids) {
            INoticeMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }
}
