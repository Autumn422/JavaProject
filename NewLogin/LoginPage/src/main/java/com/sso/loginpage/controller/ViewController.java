package com.sso.loginpage.controller;


import com.sso.loginpage.pojo.User;
import com.sso.loginpage.utils.LoginCacheUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class ViewController {
    @GetMapping("/login")
    public String toLogin(@RequestParam(required = false,defaultValue = "") String target,
                          HttpSession session,
                          @CookieValue(required = false,value = "TOKEN") Cookie cookie)
    {
        if(StringUtils.isEmpty(target))
        {
            target="http://localhost:8081/view/main";  //默认首页
        }
        //登过了
        if(cookie!=null)
        {
            String value=cookie.getValue();
            User user=LoginCacheUtil.loginUser.get(value);
            if(user!=null)
            {
                return "redirect:"+target;
            }
        }


        //地址重定向
        session.setAttribute("target",target);
        System.out.println("This Step");
        return "login";
    }
}
