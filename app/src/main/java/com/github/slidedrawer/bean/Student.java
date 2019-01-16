package com.github.slidedrawer.bean;

import org.greenrobot.greendao.annotation.Id;

/**
 * Created by wzhiqiang on 2019/1/15.
 */


public class Student {
    @Id
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
