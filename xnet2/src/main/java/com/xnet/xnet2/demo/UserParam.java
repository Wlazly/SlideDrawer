package com.xnet.xnet2.demo;

/**
 * Created by wzhiqiang on 2019/1/9.
 */

public class UserParam {
    private long birthday = -62150400000l;
    private int height = 201;
    private int height_unit = 0;
    private String nickname = "fghussts";
    private int weight = 54;
    private int weight_unit = 0;

    public UserParam(){}
    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight_unit() {
        return height_unit;
    }

    public void setHeight_unit(int height_unit) {
        this.height_unit = height_unit;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight_unit() {
        return weight_unit;
    }

    public void setWeight_unit(int weight_unit) {
        this.weight_unit = weight_unit;
    }
}
