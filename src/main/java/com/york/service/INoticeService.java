package com.york.service;

import com.github.pagehelper.PageInfo;
import com.york.entity.Notice;

import java.util.List;

/**
 * 公告业务接口
 */
public interface INoticeService {

    /**
     * 查询所有公告
     */
    PageInfo<Notice> queryAllNotice(Notice notice, Integer pageNum, Integer limit);

    /**
     * 发布公告（添加公告）
     */
    void addNotice(Notice notice);

    /**
     * 查询公告详情（这里设置公告不能随便改，不然每个人看的都不一样了，
     * 要改必须删除原来的，自己重新再发布一个）
     */
    Notice queryNoticeById(Integer id);

    /**
     * 删除
     * @param ids
     */
    void deleteNoticeByIds(List<String> ids);
}
