package com.sso.loginpage.controller;


import com.sso.loginpage.pojo.User;
import com.sso.loginpage.utils.LoginCacheUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/login")
public class LoginController {

    //写个假的用着
    private static Set<User> tempUser;
    static{
        tempUser=new HashSet<>();
        tempUser.add(new User(0,"xhb","123456"));
        tempUser.add(new User(1,"xiangling","233"));
    }

    @PostMapping
    public String doLogin(User user, HttpSession session, HttpServletResponse response)
    {
       String target= (String) session.getAttribute("target");
       System.out.println(user.getUsername());
       Optional<User> first=tempUser.stream().filter(tempUser->tempUser.getUsername().equals(user.getUsername()) &&
               tempUser.getPassword().equals(user.getPassword())).findFirst();
       if(first.isPresent())
       {
           System.out.println("success");
           //登录的存进来
           String token= UUID.randomUUID().toString();  //随机生成token，丢进去
           Cookie cookie=new Cookie("TOKEN",token);
           //cookie.setDomain("http://localhost:8081/view/main");
           cookie.setPath("/");
           response.addCookie(cookie);
           LoginCacheUtil.loginUser.put(token, first.get());
       }
       else {
           System.out.println("fail");
           session.setAttribute("msg","用户名或密码错误");
           return "login";
       }

       return "redirect:"+target;
    }
    @GetMapping("info")
    @ResponseBody
    public ResponseEntity<User> getUserInfo(String token)
    {
        if(!StringUtils.isEmpty(token))
        {
            User user=LoginCacheUtil.loginUser.get(token);
            return ResponseEntity.ok(user);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
