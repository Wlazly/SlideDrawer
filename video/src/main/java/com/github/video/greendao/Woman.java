package com.github.video.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Woman {
    @Id
    private Long id;
    private String name;
    @Generated(hash = 822672063)
    public Woman(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1175428844)
    public Woman() {
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
