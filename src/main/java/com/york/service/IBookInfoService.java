package com.york.service;

import com.github.pagehelper.PageInfo;
import com.york.entity.BookInfoEntity;

import java.util.List;

/**
 * 图书业务接口
 */
public interface IBookInfoService {

    /**
     * 查询所有记录
     */
    PageInfo<BookInfoEntity> queryBookInfoAll(BookInfoEntity bookInfoEntity, Integer pageNum, Integer limit);

    /**
     * 添加图书记录
     */
    void addBookSubmit(BookInfoEntity bookInfoEntity);
    /**
     * 修改 根据id查询记录信息
     */
    BookInfoEntity queryBookInfoById(Integer id);

    /**
     * 修改提交
     */
    void updateBookSubmit(BookInfoEntity info);

    /**
     * 根据ids删除记录信息
     */
    void deleteBookByIds(List<String> ids);

    /**
     * 根据类型获取图书数量
     */
    List<BookInfoEntity> getBookCountByType();
}
