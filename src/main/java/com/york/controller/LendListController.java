package com.york.controller;

import com.github.pagehelper.PageInfo;
import com.york.entity.BookInfoEntity;
import com.york.entity.LendEntity;
import com.york.entity.ReaderInfoEntity;
import com.york.service.IBookInfoService;
import com.york.service.ILendListService;
import com.york.service.IReaderInfoService;
import com.york.utils.DataInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class LendListController {

    @Resource
    private ILendListService ILendListService;
    @Resource
    private IReaderInfoService readerService;

    @Resource
    private IBookInfoService IBookInfoService;

    @GetMapping("/lendListIndex")
    public String lendListIndex() {
        return "lend/lendListIndex";
    }

    /**
     * 查询所有的列表
     */
    @ResponseBody
    @RequestMapping("/lendListAll")
    public DataInfo lendListAll(Integer type, String readerNumber, String name, Integer status,
                                @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "15") Integer limit) {

        LendEntity info = new LendEntity();
        info.setBackType(type);

        // 创建读者对象
        ReaderInfoEntity reader = new ReaderInfoEntity();
        reader.setReaderNumber(readerNumber);

        // 把以上对象交给info
        info.setReaderInfoEntity(reader);

        // 图书对象
        BookInfoEntity book = new BookInfoEntity();
        book.setName(name);
        book.setStatus(status);
        info.setBookInfoEntity(book);

        // 分页查询所有的记录信息
        PageInfo<LendEntity> pageInfo = ILendListService.queryLendListAll(info, page, limit);

        return DataInfo.ok("ok", pageInfo.getTotal(), pageInfo.getList());
    }


    /**
     * 添加跳转
     */
    @GetMapping("/addLendList")
    public String addLendList() {
        return "lend/addLendList";
    }

    /**
     * 借书信息提交
     * 1判断借阅号码是否存在
     * 2、可借的数据是否大于等于当前的借书量
     * 3、添加借书记录，同时改变书的状态信息
     * cardnumber:借书号码
     * ids：字符串 书id的集合
     */
    @ResponseBody
    @RequestMapping("/addLend")
    public DataInfo addLend(String readerNumber, String ids) {
        //获取图书id的集合
        List<String> list = Arrays.asList(ids.split(","));
        //判断卡号是否存在
        ReaderInfoEntity reader = new ReaderInfoEntity();
        reader.setReaderNumber(readerNumber);
        PageInfo<ReaderInfoEntity> pageInfo = readerService.queryAllReaderInfo(reader, 1, 1);
        if (pageInfo.getList().size() == 0) {
            return DataInfo.fail("卡号信息不存在");
        } else {
            ReaderInfoEntity readerCard2 = pageInfo.getList().get(0);
            //可借书
            for (String bid : list) {
                LendEntity lendEntity = new LendEntity();
                lendEntity.setReaderId(readerCard2.getId());//读者id
                lendEntity.setBookId(Integer.valueOf(bid));//书的id
                lendEntity.setLendDate(new Date());
                ILendListService.addLendListSubmit(lendEntity);
                //更变书的状态
                BookInfoEntity info = IBookInfoService.queryBookInfoById(Integer.valueOf(bid));
                //设置书的状态
                info.setStatus(1);
                IBookInfoService.updateBookSubmit(info);
            }

        }

        return DataInfo.ok();
    }


    /**
     * 删除借阅记录
     */
    @ResponseBody
    @RequestMapping("/deleteLendListByIds")
    public DataInfo deleteLendListByIds(String ids, String bookIds) {
        List list = Arrays.asList(ids.split(","));//借阅记录的id
        List blist = Arrays.asList(bookIds.split(","));//图书信息的id

        ILendListService.deleteLendListById(list, blist);
        return DataInfo.ok();
    }

    /**
     * 还书功能
     */
    @ResponseBody
    @RequestMapping("/backLendListByIds")
    public DataInfo backLendListByIds(String ids, String bookIds) {
        List list = Arrays.asList(ids.split(","));//借阅记录的id
        List blist = Arrays.asList(bookIds.split(","));//图书信息的id
        ILendListService.updateLendListSubmit(list, blist);
        return DataInfo.ok();
    }

    /**
     * 页面跳转 异常还书
     */
    @GetMapping("/excBackBook")
    public String excBackBook(HttpServletRequest request, Model model) {
        //获取借阅记录id
        String id = request.getParameter("id");
        String bId = request.getParameter("bookId");
        model.addAttribute("id", id);
        model.addAttribute("bid", bId);
        return "lend/excBackBook";
    }

    /**
     * 异常还书
     */
    @ResponseBody
    @RequestMapping("/updateLendInfoSubmit")
    public DataInfo updateLendInfoSubmit(LendEntity lendEntity) {
        ILendListService.backBook(lendEntity);
        return DataInfo.ok();
    }

    /**
     * 查阅时间线
     */
    @RequestMapping("/queryLookBookList")
    public String queryLookBookList(String flag, Integer id, Model model) {
        List<LendEntity> list = null;
        if (flag.equals("book")) {
            list = ILendListService.queryLookBookList(null, id);
        } else {
            list = ILendListService.queryLookBookList(id, null);
        }
        model.addAttribute("info", list);
        return "lend/lookBookList";
    }

    @RequestMapping("/queryLookBookList2")
    public String queryLookBookList(HttpServletRequest request, Model model) {
        ReaderInfoEntity readerInfoEntity = (ReaderInfoEntity) request.getSession().getAttribute("user");
        List<LendEntity> list = list = ILendListService.queryLookBookList(readerInfoEntity.getId(), null);
        model.addAttribute("info", list);
        return "lend/lookBookList";
    }


}
