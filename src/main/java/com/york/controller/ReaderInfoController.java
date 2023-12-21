package com.york.controller;

import com.github.pagehelper.PageInfo;
import com.york.entity.Admin;
import com.york.entity.ReaderInfo;
import com.york.service.IAdminService;
import com.york.service.IReaderInfoService;
import com.york.utils.DataInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class ReaderInfoController {

    @Resource
    private IReaderInfoService IReaderInfoService;

    @Resource
    private IAdminService IAdminService;

    /**
     * 跳转读者管理页面
     *
     * @return Jsp Page
     */
    @GetMapping("/readerIndex")
    public String readerIndex() {

        return "reader/readerIndex";
    }

    /**
     * 查询所有数据
     *
     * @return Success Request
     */
    @RequestMapping("/readerAll")
    @ResponseBody
    public DataInfo queryReaderAll(ReaderInfo readerInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit) {

        PageInfo<ReaderInfo> pageInfo = IReaderInfoService.queryAllReaderInfo(readerInfo, pageNum, limit);

        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 添加页面跳转
     *
     * @return Jsp Page
     */
    @RequestMapping("/readerAdd")
    public String readerAdd() {
        return "reader/readerAdd";
    }

    /**
     * 添加页面提交
     *
     * @return Success Request
     */
    @RequestMapping("/addReaderSubmit")
    @ResponseBody
    public DataInfo addReaderSubmit(@RequestBody ReaderInfo readerInfo) {

        readerInfo.setPassword("123456");//设置默认密码
        IReaderInfoService.addReaderInfoSubmit(readerInfo);

        return DataInfo.ok();
    }

    /**
     * 根据id查询数据再跳转到修改页面
     *
     * @return Jsp Page
     */
    @GetMapping("/queryReaderInfoById")
    public String queryReaderInfoById(Integer id, Model model) {

        ReaderInfo readerInfo = IReaderInfoService.queryReaderInfoById(id);
        model.addAttribute("info", readerInfo);

        return "reader/updateReader";
    }

    /**
     * 修改提交
     *
     * @return Success Request
     */
    @RequestMapping("/updateReaderSubmit")
    @ResponseBody
    public DataInfo updateReaderSubmit(@RequestBody ReaderInfo readerInfo) {
        IReaderInfoService.updateReaderInfoSubmit(readerInfo);

        return DataInfo.ok();
    }

    /**
     * 删除
     *
     * @return Success Request
     */
    @RequestMapping("/deleteReader")
    @ResponseBody
    public DataInfo deleteReader(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        IReaderInfoService.deleteReaderInfoByIds(list);

        return DataInfo.ok();
    }

    /**
     * 从请求中获取 session，判断用户类型，根据用户类型修改密码
     *
     * @return Success Request
     */
    @RequestMapping("/updatePwdSubmit2")
    @ResponseBody
    public DataInfo updatePwdSubmit(HttpServletRequest request, String oldPwd, String newPwd) {
        HttpSession session = request.getSession();

        // 判断用户类型，根据用户类型修改密码
        if (session.getAttribute("type") == "admin") {
            Admin admin = (Admin) session.getAttribute("user");
            Admin admin1 = IAdminService.queryAdminById(admin.getId());

            // 判断输入的旧密码是否正确
            if (!oldPwd.equals(admin1.getPassword())) {

                return DataInfo.fail("您输入的旧密码有误，请检查");
            } else {
                admin1.setPassword(newPwd);

                IAdminService.updateAdminSubmit(admin1);
            }
        } else {
            ReaderInfo readerInfo = (ReaderInfo) session.getAttribute("user");
            ReaderInfo readerInfo1 = IReaderInfoService.queryReaderInfoById(readerInfo.getId());

            if (!oldPwd.equals(readerInfo1.getPassword())) {
                return DataInfo.fail("输入的旧密码错误");
            } else {
                readerInfo1.setPassword(newPwd);
                IReaderInfoService.updateReaderInfoSubmit(readerInfo1);
            }
        }

        return DataInfo.ok();
    }
}
