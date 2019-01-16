package com.github.greendao.bean.user;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by wzhiqiang on 2019/1/11.
 */

@Entity
public class UserDbBean {

    @Id(autoincrement = true)
    private Long id;
    @Unique@NotNull
    private String userId;//用户id，唯一性

    private boolean active; //是否处于登陆在线状态

    private String userInfo;  //个人信息

    private String loginInfo; //个人登陆信息


    @Generated(hash = 938050175)
    public UserDbBean(Long id, String userId, boolean active, String userInfo,
            String loginInfo) {
        this.id = id;
        this.userId = userId;
        this.active = active;
        this.userInfo = userInfo;
        this.loginInfo = loginInfo;
    }
    @Generated(hash = 346959704)
    public UserDbBean() {
    }


    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public boolean getActive() {
        return this.active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public String getUserInfo() {
        return this.userInfo;
    }
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
    public String getLoginInfo() {
        return this.loginInfo;
    }
    public void setLoginInfo(String loginInfo) {
        this.loginInfo = loginInfo;
    }

    

}

