package com.github.greendao.bean.device;

import java.util.List;

/**
 * Created by wzhiqiang on 2019/1/11.
 */

public class DevicePropertiesBean {
    //fota当前升级的状态
    public static final int FOTA_CHECK_FAILED = 255;
    public static final int FOTA_DOWNLOAD_START = 200;
    public static final int FOTA_DOWNLOAD_FAILED = 201;
    public static final int FOTA_UPDATE_START = 101;
    public static final int FOTA_UPDATE_SUCCESS = 102;
    public static final int FOTA_UPDATE_FAILED = 202;

    public String device_model;//设备型号，如"MT30-2ALCEU1"
    public String firmware_version;//设备版本号
    public int power;//电量
    public List<String> languages;//设置支持的语言
    public String bluetooth_address;//蓝牙地址
    public int fota_sta;//fota当前的升级状态
    public boolean roaming;//是否漫游
    public String customer;//是否定制
    public String imsi;

    public DevicePropertiesBean() {}


    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getFirmware_version() {
        return firmware_version;
    }

    public void setFirmware_version(String firmware_version) {
        this.firmware_version = firmware_version;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getBluetooth_address() {
        return bluetooth_address;
    }

    public void setBluetooth_address(String bluetooth_address) {
        this.bluetooth_address = bluetooth_address;
    }

    public int getFota_sta() {
        return fota_sta;
    }

    public void setFota_sta(int fota_sta) {
        this.fota_sta = fota_sta;
    }

    public boolean isRoaming() {
        return roaming;
    }

    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
