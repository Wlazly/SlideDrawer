package com.github.greendao.bean.device;

/**
 * Created by wzhiqiang on 2019/1/11.
 */

public class DeviceLocationBean {
    //4种定位模式
    public static final int GPS = 0x001;
    public static final int BTS = 0x010;
    public static final int WIFI = 0x100;
    public static final int SOS = 0x8000;

    public double lat;//纬度
    public double lng;//精度
    public String addr;
    public int accuracy;//精度
    public long time;//定时时间
    public int type;//定位方式

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
