package com.york.controller;

import com.york.codeutil.IVerifyCodeGen;
import com.york.codeutil.SimpleCharVerifyCodeGenImpl;
import com.york.codeutil.VerifyCode;
import com.york.entity.Admin;
import com.york.entity.ReaderInfo;
import com.york.service.AdminService;
import com.york.service.ReaderInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Resource
    private AdminService adminService;

    @Resource
    private ReaderInfoService readerService;

    /**
     * 登录页面跳转
     *
     * @return Jsp Page
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 获取验证码
     *
     * @param request  Servlet 请求
     * @param response Servlet 响应
     */
    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            // 设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);

            // 获取验证码
            String code = verifyCode.getCode();

            // 将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);

            // 设置响应头
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            // 设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println("发生异常，原因是 -> " + e.getMessage());
        }
    }

    /**
     * 登录验证：从 session 中获取用户上传的数据，如果验证失败则跳转到登录页面
     */
    @RequestMapping("/loginIn")
    public String loginIn(HttpServletRequest request, Model model) {

        // 获取用户名与密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("captcha");
        String type = request.getParameter("type");

        // 判断验证码是否正确（验证码已经放入session）
        HttpSession session = request.getSession();
        String realCode = (String) session.getAttribute("VerifyCode");
        if (!realCode.toLowerCase().equals(code.toLowerCase())) {
            model.addAttribute("msg", "验证码不正确");

            return "login";
        } else {
            // 验证码正确则判断用户名和密码
            // 管理员信息
            if (type.equals("1")) {

                // 用户名和密码是否正确
                Admin admin = adminService.queryUserByNameAndPassword(username, password);

                // 该用户不存在
                if (admin == null) {
                    model.addAttribute("msg", "用户名或密码错误");

                    return "login";
                }

                session.setAttribute("user", admin);
                session.setAttribute("type", "admin");
            } else {
                // 来自读者信息表
                ReaderInfo readerInfo = readerService.queryUserInfoByNameAndPassword(username, password);
                if (readerInfo == null) {
                    model.addAttribute("msg", "用户名或密码错误");

                    return "login";
                }

                session.setAttribute("user", readerInfo);
                session.setAttribute("type", "reader");
            }

            return "index";
        }
    }

    /**
     * 退出登录
     */
    @GetMapping("loginOut")
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();

        // 结束 session
        session.invalidate();

        return "/login";
    }

}
