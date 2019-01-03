package com.github.greendao.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wzhiqiang on 2019/1/3.
 */

@Entity
public class Teacher {
    @Id
    private Long id;
    private String name;
    @Generated(hash = 1434396195)
    public Teacher(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1630413260)
    public Teacher() {
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
}
