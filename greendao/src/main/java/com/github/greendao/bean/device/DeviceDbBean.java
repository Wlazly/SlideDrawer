package com.github.greendao.bean.device;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wzhiqiang on 2019/1/11.
 */

@Entity
public class DeviceDbBean {

    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String deviceId;
    private String userId;//用户的id,不是小孩id
    private String kidId; //小孩id
    private int pid;//群组id
    private String kidInfo;//小孩信息
    private String settings;//设备设置信息
    private String properties;//设置属性信息
    private String locations;//设置定位信息
    private String contacts;//设备联系人信息



    @Generated(hash = 1915818079)
    public DeviceDbBean(Long id, String deviceId, String userId, String kidId,
            int pid, String kidInfo, String settings, String properties,
            String locations, String contacts) {
        this.id = id;
        this.deviceId = deviceId;
        this.userId = userId;
        this.kidId = kidId;
        this.pid = pid;
        this.kidInfo = kidInfo;
        this.settings = settings;
        this.properties = properties;
        this.locations = locations;
        this.contacts = contacts;
    }
    @Generated(hash = 1175437440)
    public DeviceDbBean() {
    }



    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getKidId() {
        return kidId;
    }
    public void setKidId(String kidId) {
        this.kidId = kidId;
    }
    public String getDeviceId() {
        return this.deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public int getPid() {
        return this.pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public String getKidInfo() {
        return this.kidInfo;
    }
    public void setKidInfo(String kidInfo) {
        this.kidInfo = kidInfo;
    }
    public String getSettings() {
        return this.settings;
    }
    public void setSettings(String settings) {
        this.settings = settings;
    }
    public String getProperties() {
        return this.properties;
    }
    public void setProperties(String properties) {
        this.properties = properties;
    }
    public String getLocations() {
        return this.locations;
    }
    public void setLocations(String locations) {
        this.locations = locations;
    }
    public String getContacts() {
        return this.contacts;
    }
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }



}
