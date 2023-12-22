package com.york.controller;

import com.github.pagehelper.PageInfo;
import com.york.entity.AdminEntity;
import com.york.service.IAdminService;
import com.york.utils.DataInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {

    @Resource
    private IAdminService IAdminService;

    @GetMapping("/adminIndex")
    public String adminIndex() {
        return "admin/adminIndex";
    }

    /**
     * 查询所有用户
     *
     * @param adminEntity   Admin
     * @param pageNum 当前页
     * @param limit   每页显示的条数
     * @return DataInfo
     */
    @RequestMapping("/adminAll")
    @ResponseBody
    public DataInfo queryAdminAll(AdminEntity adminEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit) {

        PageInfo<AdminEntity> pageInfo = IAdminService.queryAdminAll(adminEntity, pageNum, limit);

        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 添加页面的跳转
     *
     * @return Jsp Page
     */
    @GetMapping("/adminAdd")
    public String adminAdd() {
        return "admin/adminAdd";
    }

    /**
     * 添加提交
     *
     * @param adminEntity Admin
     * @return Request Success
     */
    @RequestMapping("/addAdminSubmit")
    @ResponseBody
    public DataInfo addBookSubmit(AdminEntity adminEntity) {

        IAdminService.addAdminSubmit(adminEntity);

        return DataInfo.ok();
    }

    /**
     * 根据 ID 查询
     *
     * @param id    用户 ID
     * @param model 用户实体
     * @return Jsp Page
     */
    @GetMapping("/queryAdminById")
    public String queryAdminById(Integer id, Model model) {

        model.addAttribute("id", id);

        return "admin/updateAdmin";
    }

    /**
     * 修改提交
     *
     * @param id     用户 ID
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return Request Success
     */
    @RequestMapping("/updatePwdSubmit")
    @ResponseBody
    public DataInfo updatePwdSubmit(Integer id, String oldPwd, String newPwd) {

        AdminEntity adminEntity = IAdminService.queryAdminById(id);

        if (!oldPwd.equals(adminEntity.getPassword())) {

            return DataInfo.fail("输入的旧密码错误");
        } else {
            adminEntity.setPassword(newPwd);
            IAdminService.updateAdminSubmit(adminEntity);

            return DataInfo.ok();
        }
    }

    /**
     * 根据选中的 ID 列表删除元素
     *
     * @param ids ID 列表
     * @return Request Success
     */
    @RequestMapping("/deleteAdminByIds")
    @ResponseBody
    public DataInfo deleteAdminByIds(String ids) {

        List<String> list = Arrays.asList(ids.split(","));
        IAdminService.deleteAdminByIds(list);

        return DataInfo.ok();
    }

}
