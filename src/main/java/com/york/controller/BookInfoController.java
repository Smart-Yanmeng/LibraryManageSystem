package com.york.controller;

import com.github.pagehelper.PageInfo;
import com.york.entity.BookInfoEntity;
import com.york.entity.TypeInfoEntity;
import com.york.service.IBookInfoService;
import com.york.service.ITypeInfoService;
import com.york.utils.DataInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
public class BookInfoController {

    @Resource
    private IBookInfoService IBookInfoService;

    @Resource
    private ITypeInfoService ITypeInfoService;

    /**
     * 图书管理首页
     *
     * @return Jsp Page
     */
    @GetMapping("/bookIndex")
    public String bookIndex() {
        return "book/bookIndex";
    }

    /**
     * 获取book信息，封装成json
     *
     * @param bookInfoEntity BookInfo
     * @param pageNum  当前页
     * @param limit    每页显示的条数
     * @return Request Success
     */
    @RequestMapping("/bookAll")
    @ResponseBody
    public DataInfo bookAll(BookInfoEntity bookInfoEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit) {
        PageInfo<BookInfoEntity> pageInfo = IBookInfoService.queryBookInfoAll(bookInfoEntity, pageNum, limit);

        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 添加页面的跳转
     *
     * @return Jsp Page
     */
    @GetMapping("/bookAdd")
    public String bookAdd() {

        return "book/bookAdd";
    }

    /**
     * 类型添加提交
     *
     * @param info BookInfo
     * @return Request Success
     */
    @RequestMapping("/addBookSubmit")
    @ResponseBody
    public DataInfo addBookSubmit(BookInfoEntity info) {
        IBookInfoService.addBookSubmit(info);

        return DataInfo.ok();
    }

    /**
     * 类型根据id查询(修改)
     *
     * @param id    id
     * @param model Model
     * @return Jsp Page
     */
    @GetMapping("/queryBookInfoById")
    public String queryTypeInfoById(Integer id, Model model) {
        BookInfoEntity bookInfoEntity = IBookInfoService.queryBookInfoById(id);
        model.addAttribute("info", bookInfoEntity);

        return "book/updateBook";
    }

    /**
     * 修改提交功能
     */

    @RequestMapping("/updateBookSubmit")
    @ResponseBody
    public DataInfo updateBookSubmit(@RequestBody BookInfoEntity info) {
        IBookInfoService.updateBookSubmit(info);
        return DataInfo.ok();
    }

    /**
     * 类型删除
     */

    @RequestMapping("/deleteBook")
    @ResponseBody
    public DataInfo deleteBook(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        IBookInfoService.deleteBookByIds(list);
        return DataInfo.ok();
    }

    @RequestMapping("/findAllList")
    @ResponseBody
    public List<TypeInfoEntity> findAll() {
        PageInfo<TypeInfoEntity> pageInfo = ITypeInfoService.queryTypeInfoAll(null, 1, 100);
        List<TypeInfoEntity> lists = pageInfo.getList();
        return lists;
    }
}
