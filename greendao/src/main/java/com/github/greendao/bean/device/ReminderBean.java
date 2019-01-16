package com.github.greendao.bean.device;

import java.util.List;

/**
 * Created by wzhiqiang on 2019/1/14.
 */

public class ReminderBean {
    public List<AlarmsBean> alarms;
    public List<TodoBean> todo;

    public ReminderBean() {
    }

    public ReminderBean(List<AlarmsBean> alarms, List<TodoBean> todo) {
        this.alarms = alarms;
        this.todo = todo;
    }

    public List<AlarmsBean> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<AlarmsBean> alarms) {
        this.alarms = alarms;
    }

    public List<TodoBean> getTodo() {
        return todo;
    }

    public void setTodo(List<TodoBean> todo) {
        this.todo = todo;
    }
}
