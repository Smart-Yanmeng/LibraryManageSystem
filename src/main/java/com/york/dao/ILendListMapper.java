package com.york.dao;

import com.york.entity.LendEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ILendListMapper {

    void deleteByPrimaryKey(Integer id);

    void insert(LendEntity record);

    void insertSelective(LendEntity record);

    LendEntity selectByPrimaryKey(Integer id);

    void updateByPrimaryKeySelective(LendEntity record);

    void updateByPrimaryKey(LendEntity record);

    /**
     * 查询所有借阅记录
     */
    List<LendEntity> queryLendListAll(LendEntity lendEntity);


    /**
     * 查询借阅阅时间线（一本书什么时候被借走过）
     */
    List<LendEntity> queryLookBookList(@Param("rid") Integer rid, @Param("bid") Integer bid);

    /**
     * 还书操作(正常还)
     */
    void updateLendListSubmit(LendEntity lendEntity);
}
