package com.york.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.york.dao.IBookInfoMapper;
import com.york.dao.ILendListMapper;
import com.york.entity.BookInfoEntity;
import com.york.entity.LendEntity;
import com.york.service.ILendListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("lendListService")
public class ILendListServiceImpl implements ILendListService {

    @Resource
    private ILendListMapper ILendListMapper;

    @Resource
    private IBookInfoMapper IBookInfoMapper;

    @Override
    public PageInfo<LendEntity> queryLendListAll(LendEntity lendEntity, int page, int limit) {

        PageHelper.startPage(page, limit);
        List<LendEntity> list = ILendListMapper.queryLendListAll(lendEntity);

        PageInfo pageInfo = new PageInfo(list);

        return pageInfo;
    }

    @Override
    public void addLendListSubmit(LendEntity lendEntity) {
        ILendListMapper.insert(lendEntity);
    }


    @Override
    public void deleteLendListById(List<String> ids, List<String> bookIds) {

        // 删除借阅记录
        for (String id : ids) {
            ILendListMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }

        // 更改图书标识，更新状态为未借出
        for (String bid : bookIds) {

            // 根据id查询图书记录信息
            BookInfoEntity bookInfoEntity = IBookInfoMapper.selectByPrimaryKey(Integer.parseInt(bid));
            bookInfoEntity.setStatus(0);

            IBookInfoMapper.updateByPrimaryKey(bookInfoEntity);
        }
    }

    @Override
    public void updateLendListSubmit(List<String> ids, List<String> bookIds) {

        for (String id : ids) {

            // 根据 ID 查询借阅记录信息
            LendEntity lendEntity = new LendEntity();
            lendEntity.setId(Integer.parseInt(id));
            lendEntity.setBackDate(new Date());
            lendEntity.setBackType(0);

            ILendListMapper.updateLendListSubmit(lendEntity);
        }

        //更改图书标识，更新状态为未借出
        for (String bid : bookIds) {

            //根据id查询图书记录信息
            BookInfoEntity bookInfoEntity = IBookInfoMapper.selectByPrimaryKey(Integer.parseInt(bid));
            bookInfoEntity.setStatus(0);//该为未借出

            IBookInfoMapper.updateByPrimaryKey(bookInfoEntity);
        }
    }

    @Override
    public void backBook(LendEntity lendEntity) {
        LendEntity lend = new LendEntity();

        lend.setId(lendEntity.getId());
        lend.setBackType(lendEntity.getBackType());
        lend.setBackDate(new Date());
        lend.setExceptRemarks(lendEntity.getExceptRemarks());
        lend.setBookId(lendEntity.getBookId());

        ILendListMapper.updateLendListSubmit(lend);

        // 判断异常还书 如果是延期或者正常还书，需要更改书的状态
        if (lend.getBackType() == 0 || lend.getBackType() == 1) {

            BookInfoEntity bookInfoEntity = IBookInfoMapper.selectByPrimaryKey(lend.getBookId());
            bookInfoEntity.setStatus(0);

            IBookInfoMapper.updateByPrimaryKey(bookInfoEntity);
        }
    }

    @Override
    public List<LendEntity> queryLookBookList(Integer rid, Integer bid) {

        return ILendListMapper.queryLookBookList(rid, bid);
    }

}
