package com.xnet.xnet2.demo;

/*
 * Created by qianli.ma on 2019/1/8 0008.
 */
public class LoginParam {
    private String username;
    private String password;

    public LoginParam() {
    }

    public LoginParam(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
