package com.github.greendao.bean.device;

/**
 * Created by wzhiqiang on 2019/1/11.
 */

public class DeviceKidBean {


    public String gid; //群组id
    public String uid; //kid的uid
    public String nickname;
    public String profile;//小孩的头像服务器路径
    public String number;//小孩手机号码
    public String gender;
    public int height;
    public int weight;
    public int height_unit;//0:metric,1:british
    public int weight_unit;//0:metric,1:british
    public long birthday;
    public String profile_local;//小孩的头像的本地文件的路径
    public boolean online;//设置是否在线
    public String owner;//管理员的uid

    public String getGid() {
        return gid;
    }

    public String getProfile_local() {
        return profile_local;
    }

    public void setProfile_local(String profile_local) {
        this.profile_local = profile_local;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight_unit() {
        return height_unit;
    }

    public void setHeight_unit(int height_unit) {
        this.height_unit = height_unit;
    }

    public int getWeight_unit() {
        return weight_unit;
    }

    public void setWeight_unit(int weight_unit) {
        this.weight_unit = weight_unit;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }


    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
