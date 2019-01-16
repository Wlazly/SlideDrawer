package com.github.greendao.bean.device;

/**
 * Created by wzhiqiang on 2019/1/11.
 */

public class DeviceContactBean {
    private String uid;
    private int type;
    private String nickname;
    private String identity;
    private String phone;
    private String phone_country;
    private int flag;
    private boolean admin;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone_country() {
        return phone_country;
    }

    public void setPhone_country(String phone_country) {
        this.phone_country = phone_country;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
