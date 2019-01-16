package com.github.greendao.bean.device;

/**
 * Created by wzhiqiang on 2019/1/14.
 */

public class PeriodsBean {
    public int start;
    public int end;

    public PeriodsBean() {
    }

    public PeriodsBean(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
