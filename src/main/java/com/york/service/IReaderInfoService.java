package com.york.service;

import com.github.pagehelper.PageInfo;
import com.york.entity.ReaderInfoEntity;

import java.util.List;

/**
 * 读者业务接口
 */
public interface IReaderInfoService {

    /**
     * 查询所有记录
     */
    PageInfo<ReaderInfoEntity> queryAllReaderInfo(ReaderInfoEntity readerInfoEntity, Integer pageNum, Integer limit);

    /**
     * 添加
     */
    void addReaderInfoSubmit(ReaderInfoEntity readerInfoEntity);

    /**
     * 查询（修改前先查询）
     */
    ReaderInfoEntity queryReaderInfoById(Integer id);

    /**
     * 修改提交
     */
    void updateReaderInfoSubmit(ReaderInfoEntity readerInfoEntity);

    /**
     * 删除
     */
    void deleteReaderInfoByIds(List<String> ids);

    /**
     * 根据用户名和密码查询用户信息
     */
    ReaderInfoEntity queryUserInfoByNameAndPassword(String username, String password);
}
