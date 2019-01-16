package com.github.greendao.bean.device;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzhiqiang on 2019/1/15.
 */

public class DeviceBean {

    private String deviceId;
    private String userId;//用户的id,不是小孩id
    private String uid; //小孩id
    private int pid;//群组id
    private DeviceKidBean kidInfo;//小孩信息
    private DeviceSettingsBean settings;//设备设置信息
    private DevicePropertiesBean properties;//设置属性信息
    private DeviceLocationBean locations;//设置定位信息
    private List<DeviceContactBean> contactList;//设备联系人信息

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKidId() {
        return uid;
    }

    public void setKidId(String kidId) {
        this.uid = kidId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public DeviceKidBean getKidInfo() {
        return kidInfo;
    }

    public void setKidInfo(DeviceKidBean kidInfo) {
        this.kidInfo = kidInfo;
    }

    public DeviceSettingsBean getSettings() {
        return settings;
    }

    public void setSettings(DeviceSettingsBean settings) {
        this.settings = settings;
    }

    public DevicePropertiesBean getProperties() {
        return properties;
    }

    public void setProperties(DevicePropertiesBean properties) {
        this.properties = properties;
    }

    public DeviceLocationBean getLocations() {
        return locations;
    }

    public void setLocations(DeviceLocationBean locations) {
        this.locations = locations;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<DeviceContactBean> getContactList() {
        return contactList;
    }

    public void setContactList(List<DeviceContactBean> contactList) {
        this.contactList = contactList;
    }

    public void copy(DeviceDbBean deviceDbBean){
        this.userId = deviceDbBean.getUserId();
        this.uid = deviceDbBean.getKidId();
        this.deviceId = deviceDbBean.getDeviceId();
        this.pid = deviceDbBean.getPid();
        this.kidInfo = JSONObject.parseObject(deviceDbBean.getKidInfo(),DeviceKidBean.class);
        this.settings = JSONObject.parseObject(deviceDbBean.getSettings(),DeviceSettingsBean.class);
        this.properties = JSONObject.parseObject(deviceDbBean.getProperties(),DevicePropertiesBean.class);
        this.locations = JSONObject.parseObject(deviceDbBean.getLocations(),DeviceLocationBean.class);
        this.contactList = JSON.parseObject(deviceDbBean.getContacts(), new TypeReference<ArrayList<DeviceContactBean>>(){});
    }


    public DeviceDbBean getDbBean(DeviceDbBean deviceDbBean){
        deviceDbBean.setUserId(this.userId);
        deviceDbBean.setDeviceId(this.deviceId);
        if (!TextUtils.isEmpty(this.uid)){
            deviceDbBean.setKidId(this.uid);
        }
        if (this.pid != 0) {
            deviceDbBean.setPid(this.pid);
        }
        if (kidInfo != null){
            deviceDbBean.setKidInfo(JSONObject.toJSONString(kidInfo));
        }
        if (settings != null) {
            deviceDbBean.setSettings(JSONObject.toJSONString(settings));
        }
        if (properties != null) {
            deviceDbBean.setProperties(JSONObject.toJSONString(properties));
        }
        if (locations != null) {
            deviceDbBean.setLocations(JSONObject.toJSONString(locations));
        }
        if (contactList != null) {
            deviceDbBean.setContacts(JSONObject.toJSONString(contactList));
        }
        return deviceDbBean;
    }
}
