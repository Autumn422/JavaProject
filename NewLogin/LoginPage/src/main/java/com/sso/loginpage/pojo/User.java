package com.sso.loginpage.pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data  //get,set构造
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class User {
    private Integer id;
    private String username;
    private String password;



}
