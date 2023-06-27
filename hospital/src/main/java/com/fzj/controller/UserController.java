package com.fzj.controller;

import com.fzj.pojo.User;
import com.fzj.service.HospitalService;
import com.fzj.service.OrderRecordsService;
import com.fzj.service.UserService;
import com.fzj.utils.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRecordsService orderRecordsService;


    @Autowired
    private HospitalService hospitalService;

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 登录界面
     * @param session
     * @return
     */
    @GetMapping("/login")
    public String login(HttpSession session){
        session.invalidate();
        return "login";
    }

    /**
     * 登录验证
     */
    @PostMapping("/login")
    public String login(Model model, String email, String password,String verficationCode,HttpSession session, HttpServletRequest request){
        //正确验证码
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        int ans = userService.login(email, password, request);
        String error = "";
        User user = userService.findUserByEmail(email);
        if(!verficationCode.equalsIgnoreCase(verifyCode)||verficationCode==null||verifyCode==null){
            error="验证码错误";
            log.info(error);
            model.addAttribute("error",error);
            return "login";
        }
        else{
            if(ans==2){//登录成功
                model.addAttribute("user", user);
                session.setAttribute("user", user);
                return "loginsucess";
            }
            else if(ans==1){//密码错误
                error="密码错误";
                log.info(error);
                model.addAttribute("error",error);
                return "login";
            }
            else{//用户不存在
                error="该用户不存在";
                log.info(error);
                model.addAttribute("error",error);
                return "login";
            }
        }
    }

    /**
     * 注册页面
     */
    @GetMapping("/sign")
    public String sign(HttpSession session){
        session.invalidate();
        return "sign";
    }

    /**
     * 注册
     */
    @PostMapping("/sign")
    public String sign(Model model, User user, HttpServletRequest request, @RequestParam("confirmPassword") String confirmPassword){
        user.setEmail(user.getEmail().trim());
        if (!user.getPassword().equals(confirmPassword)) {
            String error = "密码和确认密码不匹配";
            log.info(error);
            model.addAttribute("error", error);
            return "sign";
        }
        int ans = userService.sign(user, request);
        String error = "";
        if(ans==0){
            error="身份证被占用";
            log.info(error);
            model.addAttribute("error",error);
            return "sign";
        }
        else if(ans==1){
            error="邮箱被占用";
            log.info(error);
            model.addAttribute("error",error);
            return "sign";
        }
        else if(ans==2){
            error="手机号被占用";
            log.info(error);
            model.addAttribute("error",error);
            return "sign";
        }
        else{
            error="注册成功，请登录账号";
            log.info(error);
            model.addAttribute("error",error);
            return "login";
        }
    }

    /**
     * 修改密码页面
     * @param session
     * @return
     */
    @GetMapping("/modifyPassword")
    public String modifyPassword(HttpSession session){
        session.invalidate();
        return "modifyPassword";
    }

    /**
     * 修改密码
     * @param model
     * @param email
     * @param old_password
     * @param new_password
     * @return
     */
    @PostMapping("/modifyPassword")
    public String modifyPassword(Model model,String email,String old_password,String new_password){
        String error="";
        User user = userService.findUserByEmail(email);
        if(user.getPassword().equals(MD5.getMD5(old_password))){
            userService.modifyPassword(email,new_password);
            error="密码修改成功";
            log.info(error);
            model.addAttribute("error",error);
            return "login";
        }
        else{
             error="密码与原密码不一致，请重试";
             model.addAttribute("error",error);
             return "modifyPassword";
        }
    }

    /**
     * 个人中心页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/PersonalCenter/{id}")
    public String personal(Model model,@PathVariable("id") Integer id){
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
        return "personalCenter";
    }

    /**
     * 个人信息编辑的页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    /**
     * 编辑个人信息
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/editUser/{id}")
    public String updateUser(@PathVariable("id") Integer id,User user) {
        userService.updateUserInfo(user);
        return "redirect:/PersonalCenter/{id}";
    }
}
