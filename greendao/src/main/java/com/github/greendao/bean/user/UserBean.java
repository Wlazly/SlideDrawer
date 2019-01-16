package com.github.greendao.bean.user;

/**
 * Created by wzhiqiang on 2019/1/10.
 */

public class UserBean {

    public String uid;
    public String username;
    public String nickname;
    public String profile;
    public String email;
    public String phone;
    public String gender;
    public int height;
    public int weight;
    public int unit_value;//身高的单位，0:metric,1:british
    public int unit_weight_value;//体重的单位，0:metric,1:british
    public int height_unit;
    public int weight_unit;
    public int orientation;//手表携带位置
    public long birthday;
    public long mtime;//更新时间(更新数据时会自动修改,不用手动上传该参数)

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getUnit_value() {
        return unit_value;
    }

    public void setUnit_value(int unit_value) {
        this.unit_value = unit_value;
    }

    public int getUnit_weight_value() {
        return unit_weight_value;
    }

    public void setUnit_weight_value(int unit_weight_value) {
        this.unit_weight_value = unit_weight_value;
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

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public long getMtime() {
        return mtime;
    }

    public void setMtime(long mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", profile='" + profile + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", unit_value=" + unit_value +
                ", unit_weight_value=" + unit_weight_value +
                ", height_unit=" + height_unit +
                ", weight_unit=" + weight_unit +
                ", orientation=" + orientation +
                ", birthday=" + birthday +
                ", mtime=" + mtime +
                '}';
    }
}
