package com.york.service;

import com.github.pagehelper.PageInfo;
import com.york.entity.LendEntity;

import java.util.List;

/**
 * 借阅记录业务接口
 */
public interface ILendListService {

    /**
     * 查询所有借阅记录
     *
     * @param lendEntity LendList
     * @param page     当前页
     * @param limit    每页显示的条数
     * @return PageInfo
     */
    PageInfo<LendEntity> queryLendListAll(LendEntity lendEntity, int page, int limit);

    /**
     * 添加借阅记录
     *
     * @param lendEntity LendList
     */
    void addLendListSubmit(LendEntity lendEntity);


    /**
     * 删除
     *
     * @param ids 借阅记录 ID 集合
     */
    void deleteLendListById(List<String> ids, List<String> bookIds);

    /**
     * 还书
     *
     * @param ids     借阅记录 ID 集合
     * @param bookIds 图书 ID 集合
     */
    void updateLendListSubmit(List<String> ids, List<String> bookIds);

    /**
     * 异常还书
     *
     * @param lendEntity LendList
     */
    void backBook(LendEntity lendEntity);

    /**
     * 时间线查询
     *
     * @param rid 读者 ID
     * @param bid 图书 ID
     * @return List<LendList>
     */
    List<LendEntity> queryLookBookList(Integer rid, Integer bid);
}
