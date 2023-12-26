package com.york.controller;

import com.github.pagehelper.PageInfo;
import com.york.entity.Notice;
import com.york.service.INoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BaseController {

    @Resource
    private INoticeService INoticeService;

    /**
     * 首页
     *
     * @return Jsp Page
     */
    @GetMapping("/index")
    public String index() {

        return "index";
    }

    /**
     * 欢迎页面跳转
     *
     * @param model Model
     * @return Jsp Page
     */
    @GetMapping("/welcome")
    public String welcome(Model model) {
        // 提供公告信息
        PageInfo<Notice> pageInfo = INoticeService.queryAllNotice(null, 1, 5);
        if (pageInfo != null) {
            List<Notice> noticeList = pageInfo.getList();
            model.addAttribute("noticeList", noticeList);
        }

        return "welcome";
    }

    @GetMapping("/updatePassword")
    public String updatePwd() {
        return "pwdUpdate/updatePwd";
    }

}
