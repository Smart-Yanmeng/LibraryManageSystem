package com.york.controller;

import com.york.entity.BookInfoEntity;
import com.york.service.IBookInfoService;
import com.york.service.ITypeInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class StatisticsController {

    @Resource
    private IBookInfoService IBookInfoService;

    @Resource
    private ITypeInfoService ITypeInfoService;

    /**
     * 统计首页
     *
     * @param model 图书模型
     * @return Jsp Page
     */
    @GetMapping("statisticIndex")
    public String statistics(Model model){

        // 根据图书类型查询图书数量
        List<BookInfoEntity> list = IBookInfoService.getBookCountByType();
        model.addAttribute("list",list);

        return "count/statisticIndex";
    }
}
