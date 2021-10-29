package com.sso.loginpage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
    @GetMapping("/login")
    public String toLogin()
    {
        System.out.println("This Step");
        return "login";
    }
}
