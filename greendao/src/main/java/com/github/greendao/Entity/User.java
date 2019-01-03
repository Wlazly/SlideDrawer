package com.github.greendao.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wzhiqiang on 2018/12/25.
 */
@Entity
public class User {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private int age;
    private String address;
    private String school;
    private String sex;
    private String education;
    private String likes;
    private String hates;

    public static final String LIKES = "likes";
    public static final String HATES = "hates";



    @Generated(hash = 1710933067)
    public User(Long id, String name, int age, String address, String school,
            String sex, String education, String likes, String hates) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.school = school;
        this.sex = sex;
        this.education = education;
        this.likes = likes;
        this.hates = hates;
    }

    public User(String name, int age, String address, String school, String sex, String education, String likes, String hates) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.school = school;
        this.sex = sex;
        this.education = education;
        this.likes = likes;
        this.hates = hates;
    }

    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSchool() {
        return this.school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getEducation() {
        return this.education;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public String getLikes() {
        return this.likes;
    }
    public void setLikes(String likes) {
        this.likes = likes;
    }
    public String getHates() {
        return this.hates;
    }
    public void setHates(String hates) {
        this.hates = hates;
    }


    
}
