package com.york.dao;

import com.york.entity.ReaderInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IReaderInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ReaderInfoEntity record);

    int insertSelective(ReaderInfoEntity record);

    ReaderInfoEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReaderInfoEntity record);

    int updateByPrimaryKey(ReaderInfoEntity record);

    /**
     * 查询所有记录信息
     */
    List<ReaderInfoEntity> queryAllReaderInfo(ReaderInfoEntity readerInfoEntity);

    /**
     * 根据用户名和密码查询用户信息
     */
    ReaderInfoEntity queryUserInfoByNameAndPassword(@Param("username") String username, @Param("password") String password);
}
