package com.york.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.york.dao.IBookInfoMapper;
import com.york.dao.ILendListMapper;
import com.york.entity.BookInfo;
import com.york.entity.LendList;
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
    public PageInfo<LendList> queryLendListAll(LendList lendList, int page, int limit) {

        PageHelper.startPage(page, limit);
        List<LendList> list = ILendListMapper.queryLendListAll(lendList);

        PageInfo pageInfo = new PageInfo(list);

        return pageInfo;
    }

    @Override
    public void addLendListSubmit(LendList lendList) {
        ILendListMapper.insert(lendList);
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
            BookInfo bookInfo = IBookInfoMapper.selectByPrimaryKey(Integer.parseInt(bid));
            bookInfo.setStatus(0);

            IBookInfoMapper.updateByPrimaryKey(bookInfo);
        }
    }

    @Override
    public void updateLendListSubmit(List<String> ids, List<String> bookIds) {

        for (String id : ids) {

            // 根据 ID 查询借阅记录信息
            LendList lendList = new LendList();
            lendList.setId(Integer.parseInt(id));
            lendList.setBackDate(new Date());
            lendList.setBackType(0);

            ILendListMapper.updateLendListSubmit(lendList);
        }

        //更改图书标识，更新状态为未借出
        for (String bid : bookIds) {

            //根据id查询图书记录信息
            BookInfo bookInfo = IBookInfoMapper.selectByPrimaryKey(Integer.parseInt(bid));
            bookInfo.setStatus(0);//该为未借出

            IBookInfoMapper.updateByPrimaryKey(bookInfo);
        }
    }

    @Override
    public void backBook(LendList lendList) {
        LendList lend = new LendList();

        lend.setId(lendList.getId());
        lend.setBackType(lendList.getBackType());
        lend.setBackDate(new Date());
        lend.setExceptRemarks(lendList.getExceptRemarks());
        lend.setBookId(lendList.getBookId());

        ILendListMapper.updateLendListSubmit(lend);

        // 判断异常还书 如果是延期或者正常还书，需要更改书的状态
        if (lend.getBackType() == 0 || lend.getBackType() == 1) {

            BookInfo bookInfo = IBookInfoMapper.selectByPrimaryKey(lend.getBookId());
            bookInfo.setStatus(0);

            IBookInfoMapper.updateByPrimaryKey(bookInfo);
        }
    }

    @Override
    public List<LendList> queryLookBookList(Integer rid, Integer bid) {

        return ILendListMapper.queryLookBookList(rid, bid);
    }

}
