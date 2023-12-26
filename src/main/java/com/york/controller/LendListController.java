package com.york.controller;

import com.github.pagehelper.PageInfo;
import com.york.entity.BookInfo;
import com.york.entity.LendList;
import com.york.entity.ReaderInfo;
import com.york.service.IBookInfoService;
import com.york.service.ILendListService;
import com.york.service.IReaderInfoService;
import com.york.entity.DataInfo;
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

        LendList info = new LendList();
        info.setBackType(type);

        // 创建读者对象
        ReaderInfo reader = new ReaderInfo();
        reader.setReaderNumber(readerNumber);

        // 把以上对象交给info
        info.setReaderInfo(reader);

        // 图书对象
        BookInfo book = new BookInfo();
        book.setName(name);
        book.setStatus(status);
        info.setBookInfo(book);

        // 分页查询所有的记录信息
        PageInfo<LendList> pageInfo = ILendListService.queryLendListAll(info, page, limit);

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

        // 获取图书id的集合
        String[] list = ids.split(",");

        // 判断卡号是否存在
        ReaderInfo reader = new ReaderInfo();
        reader.setReaderNumber(readerNumber);
        PageInfo<ReaderInfo> pageInfo = readerService.queryAllReaderInfo(reader, 1, 1);
        if (pageInfo.getList().isEmpty()) {
            return DataInfo.fail("卡号信息不存在");
        } else {
            ReaderInfo readerCard2 = pageInfo.getList().get(0);
            // 可借书
            for (String bid : list) {

                LendList lendList = new LendList();

                lendList.setReaderId(readerCard2.getId());
                lendList.setBookId(Integer.valueOf(bid));
                lendList.setLendDate(new Date());

                ILendListService.addLendListSubmit(lendList);

                // 更变书的状态
                BookInfo info = IBookInfoService.queryBookInfoById(Integer.valueOf(bid));

                // 设置书的状态
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

        List<String> list = Arrays.asList(ids.split(","));
        List<String> blist = Arrays.asList(bookIds.split(","));

        ILendListService.deleteLendListById(list, blist);
        return DataInfo.ok();
    }

    /**
     * 还书功能
     */
    @ResponseBody
    @RequestMapping("/backLendListByIds")
    public DataInfo backLendListByIds(String ids, String bookIds) {

        List<String> list = Arrays.asList(ids.split(","));
        List<String> blist = Arrays.asList(bookIds.split(","));
        ILendListService.updateLendListSubmit(list, blist);

        return DataInfo.ok();
    }

    /**
     * 页面跳转 异常还书
     */
    @GetMapping("/excBackBook")
    public String excBackBook(HttpServletRequest request, Model model) {

        // 获取借阅记录 ID
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
    public DataInfo updateLendInfoSubmit(LendList lendList) {

        ILendListService.backBook(lendList);

        return DataInfo.ok();
    }

    /**
     * 查阅时间线
     */
    @RequestMapping("/queryLookBookList")
    public String queryLookBookList(String flag, Integer id, Model model) {

        List<LendList> list = null;

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

        ReaderInfo readerInfo = (ReaderInfo) request.getSession().getAttribute("user");
        List<LendList> list = ILendListService.queryLookBookList(readerInfo.getId(), null);
        model.addAttribute("info", list);

        return "lend/lookBookList";
    }
}
