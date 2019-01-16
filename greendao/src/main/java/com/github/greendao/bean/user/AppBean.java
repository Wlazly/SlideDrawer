package com.github.greendao.bean.user;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wzhiqiang on 2019/1/11.
 */

@Entity
public class AppBean  {

    @Id(autoincrement = true)
    private Long id;

    //版本号
    private String versionCode;
    //版本名
    private String  versionName;
    //保存时间
    private long upTime;


    @Generated(hash = 1233539558)
    public AppBean(Long id, String versionCode, String versionName, long upTime) {
        this.id = id;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.upTime = upTime;
    }

    @Generated(hash = 285800313)
    public AppBean() {
    }


    public long getUpTime() {
        return upTime;
    }

    public void setUpTime(long upTime) {
        this.upTime = upTime;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
