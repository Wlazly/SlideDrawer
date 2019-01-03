package com.github.swiperecyclerlayout.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Man {
    @Id
    private Long id;
    private String name;
    @Generated(hash = 569773815)
    public Man(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 647924116)
    public Man() {
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
