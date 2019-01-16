package com.github.greendao.bean.user;

import android.text.TextUtils;

/**
 * Created by wzhiqiang on 2018/12/25.
 */

public class LoginBean {

    private String token;
    private String uid;
    private String platform;
    private long expired_at;

    enum Platform {
        TWITTER("twitter"),
        FACEBOOK("facebook");
        private String platFormName;

        Platform(String platFormName){
            this.platFormName = platFormName;
        }

        public String getPlatFormName() {
            return platFormName;
        }

        public void setPlatFormName(String platFormName) {
            this.platFormName = platFormName;
        }
    }


    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public long getExpired_at() {
        return this.expired_at;
    }
    public void setExpired_at(long expired_at) {
        this.expired_at = expired_at;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getPlatform() {
        return this.platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }


    @Override
    public String toString() {
        return "LoginBean{" +
                "token='" + token + '\'' +
                ", expired_at=" + expired_at +
                ", uid='" + uid + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }

    public void copy(LoginBean bean){
        this.platform = !TextUtils.isEmpty(bean.getPlatform())? bean.getPlatform():this.platform;
        this.uid = bean.getUid();
        this.token = bean.getToken();
        this.expired_at = bean.getExpired_at();
    }
}
