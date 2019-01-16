package com.xnet.xnet2.demo;

/*
 * Created by qianli.ma on 2019/1/8 0008.
 */
public class LoginAction {

    public void login(String userName, String password,String serurl) {

        LoginHelper loginHelper = new LoginHelper();
        loginHelper.setOnSuccessByValueListener(loginbean -> {
            // todo 请求成功并返回实例
        });
        loginHelper.setOnSuccessNoValueListener(() -> {
            // todo 请求成功但返回实例
        });
        loginHelper.setOnAppErrorListener(ex -> {
            // todo APP请求失败,如链接错误
        });
        loginHelper.setOnServerErrorListener(error -> {
            // todo 服务器返回错误, 如密码错误
        });
        loginHelper.setOnCancelListener(cex -> {
            // todo 取消请求
        });
        loginHelper.setOnFinishListener(() -> {
            // todo 请求结束
        });
        loginHelper.get(userName, password,serurl);
    }
}
