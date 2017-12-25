package com.pxc.user.controller;

import com.pxc.user.bean.User;
import com.pxc.user.service.UserService;
import com.pxc.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by PXC on 2017/6/24.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
 /**
   *@Author Ethan
   *@Date 2017/6/28 18:34
   *@Description 登录验证
   */
    @RequestMapping("/login")
    @ResponseBody
    public String login(String username, String password, HttpSession session) {
        try {
            String passwordMD5 = SecurityUtil.encryptPassword(password);
            User user = userService.findUserByUsernameAndPassword(username, passwordMD5);
            if (user != null) {
                session.setAttribute("user", user);
                return "login_success";
            } else {
                return "login_false";
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "login_false";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "login_false";
        }

    }
/**
  *@Author Ethan
  *@Date 2017/6/28 18:36
  *@Description 登出操作
  */
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        try {
            response.sendRedirect("/post");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
 /**
   *@Author Ethan
   *@Date 2017/6/28 18:36
   *@Description 注册用户
   */
    @RequestMapping("/register")
    @ResponseBody
    public String register(MultipartHttpServletRequest multipartHttpServletRequest, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        try {
            String headPic = file.getOriginalFilename();
            String username = multipartHttpServletRequest.getParameter("username");
            String password = multipartHttpServletRequest.getParameter("password");
            String passwordMD5 = SecurityUtil.encryptPassword(password);
            String email = multipartHttpServletRequest.getParameter("email");
            String selfIntroduction = multipartHttpServletRequest.getParameter("selfIntroduction");
            User user = new User();
            user.setEmail(email);
            user.setHeadPic(headPic);
            user.setPassword(passwordMD5);
            user.setSelfIntroduction(selfIntroduction);
            user.setUsername(username);

            File uploadF = new File("/var/www/imgs/" + headPic);
            User userDB = userService.findUserByUsernameAndPassword(username, passwordMD5);
            if(userDB==null){
                if (userService.createUser(user)) {
                    file.transferTo(uploadF);
                    return "注册成功";
                } else {
                    System.out.println("注册失败");
                    return "注册失败";
                }
            }else{
                return "用户名和密码已经存在";
            }



        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
            return "注册失败";

        }
            return "success";
    }

}
