package com.github.greendao.bean.device;

import java.util.List;

/**
 * Created by wzhiqiang on 2019/1/14.
 */

public class AlarmsBean {
    public boolean active;
    public long alarm;
    public List<Integer> days;

    public AlarmsBean(boolean active, long alarm, List<Integer> days) {
        this.active = active;
        this.alarm = alarm;
        this.days = days;
    }

    public AlarmsBean() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getAlarm() {
        return alarm;
    }

    public void setAlarm(long alarm) {
        this.alarm = alarm;
    }

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }
}
