package com.sso.loginpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApp {
    public static void main(String[] args)
    {
        SpringApplication.run(LoginApp.class, args);
        System.out.println( "这是LoginPage" );
    }
}
