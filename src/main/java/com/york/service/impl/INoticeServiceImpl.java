package com.york.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.york.dao.INoticeMapper;
import com.york.entity.Notice;
import com.york.service.INoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("noticeService")
public class INoticeServiceImpl implements INoticeService {

    @Resource
    private INoticeMapper INoticeMapper;

    @Override
    public PageInfo<Notice> queryAllNotice(Notice notice, Integer pageNum, Integer limit) {

        PageHelper.startPage(pageNum, limit);
        List<Notice> noticeList = INoticeMapper.queryNoticeAll(notice);

        return new PageInfo<>(noticeList);
    }

    @Override
    public void addNotice(Notice notice) {
        INoticeMapper.insert(notice);
    }

    @Override
    public Notice queryNoticeById(Integer id) {

        return INoticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteNoticeByIds(List<String> ids) {

        for (String id : ids) {
            INoticeMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }
}
