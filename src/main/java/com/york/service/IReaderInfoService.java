package com.york.service;

import com.github.pagehelper.PageInfo;
import com.york.entity.ReaderInfo;

import java.util.List;

/**
 * 读者业务接口
 */
public interface IReaderInfoService {

    /**
     * 查询所有记录
     */
    PageInfo<ReaderInfo> queryAllReaderInfo(ReaderInfo readerInfo,Integer pageNum,Integer limit);

    /**
     * 添加
     */
    void addReaderInfoSubmit(ReaderInfo readerInfo);

    /**
     * 查询（修改前先查询）
     */
    ReaderInfo queryReaderInfoById(Integer id);

    /**
     * 修改提交
     */
    void updateReaderInfoSubmit(ReaderInfo readerInfo);

    /**
     * 删除
     */
    void deleteReaderInfoByIds(List<String> ids);

    /**
     * 根据用户名和密码查询用户信息
     */
    ReaderInfo queryUserInfoByNameAndPassword(String username,String password);
}
