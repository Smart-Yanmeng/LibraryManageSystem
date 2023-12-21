package com.york.controller;

import com.github.pagehelper.PageInfo;
import com.york.entity.Notice;
import com.york.service.INoticeService;
import com.york.utils.DataInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeController {

    @Resource
    private INoticeService INoticeService;

    /**
     * 后台公告
     *
     * @return Jsp Page
     */
    @GetMapping("/noticeIndexOfBack")
    public String noticeIndexOfBack() {

        return "notice/noticeIndexOfBack";
    }

    /**
     * 后台公告
     *
     * @return Jsp Page
     */
    @GetMapping("/noticeIndexOfReader")
    public String noticeIndexOfReader() {

        return "notice/noticeIndexOfReader";
    }

    /**
     * 查询所有公告信息
     *
     * @return Success Request
     */
    @RequestMapping("/noticeAll")
    @ResponseBody
    public DataInfo noticeAll(Notice notice, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit) {

        PageInfo<Notice> pageInfo = INoticeService.queryAllNotice(notice, pageNum, limit);

        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 添加
     *
     * @return Jsp Page
     */
    @GetMapping("/noticeAdd")
    public String noticeAdd() {

        return "notice/noticeAdd";
    }

    /**
     * 添加提交
     *
     * @return Success Request
     */
    @RequestMapping("/addNoticeSubmit")
    @ResponseBody
    public DataInfo addNoticeSubmit(Notice notice) {

        // 主题和内容可以页面获取，作者和时间在后台自动获取
        notice.setAuthor("admin");
        notice.setCreateDate(new Date());
        INoticeService.addNotice(notice);

        return DataInfo.ok();
    }

    /**
     * 查看详情（修改）
     *
     * @return Jsp Page
     */
    @GetMapping("/queryNoticeById")
    public String queryNoticeById(Integer id, Model model) {

        Notice notice = INoticeService.queryNoticeById(id);
        model.addAttribute("info", notice);

        return "notice/updateNotice";
    }

    /**
     * 删除公告
     *
     * @return Success Request
     */
    @RequestMapping("/deleteNoticeByIds")
    @ResponseBody
    public DataInfo deleteNoticeByIds(String ids) {

        List<String> list = Arrays.asList(ids.split(","));
        INoticeService.deleteNoticeByIds(list);

        return DataInfo.ok();
    }
}
