package com.github.greendao.bean.device;

import java.util.List;

/**
 * Created by wzhiqiang on 2019/1/14.
 */

public class SchoolTimeBean {
    public boolean active;
    public List<Integer> days;
    public List<PeriodsBean> periods;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public List<PeriodsBean> getPeriods() {
        return periods;
    }

    public void setPeriods(List<PeriodsBean> periods) {
        this.periods = periods;
    }
}
