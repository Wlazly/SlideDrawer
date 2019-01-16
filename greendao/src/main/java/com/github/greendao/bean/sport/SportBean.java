package com.github.greendao.bean.sport;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wzhiqiang on 2019/1/15.
 */
@Entity
public class SportBean {

    @Id(autoincrement = true)
    private Long id;
    private String userId;
    private String deviceId;
    private long startTime;
    private long endTime;
    private float timezone;
    private long totalSteps;
    private float totalDistance;
    private int totalCalories;
    private int totalDuration;
    private int goal;
    private String actionType;//运动类型
    private long dateHour;
    //扩展字段
    private long xinfo1;
    private long xinfo2;
    private String xinfo3;
    private String xinfo4;

    @Generated(hash = 638654070)
    public SportBean(Long id, String userId, String deviceId, long startTime,
            long endTime, float timezone, long totalSteps, float totalDistance,
            int totalCalories, int totalDuration, int goal, String actionType,
            long dateHour, long xinfo1, long xinfo2, String xinfo3, String xinfo4) {
        this.id = id;
        this.userId = userId;
        this.deviceId = deviceId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timezone = timezone;
        this.totalSteps = totalSteps;
        this.totalDistance = totalDistance;
        this.totalCalories = totalCalories;
        this.totalDuration = totalDuration;
        this.goal = goal;
        this.actionType = actionType;
        this.dateHour = dateHour;
        this.xinfo1 = xinfo1;
        this.xinfo2 = xinfo2;
        this.xinfo3 = xinfo3;
        this.xinfo4 = xinfo4;
    }
    @Generated(hash = 84405280)
    public SportBean() {
    }


    
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDeviceId() {
        return this.deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public long getStartTime() {
        return this.startTime;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public long getEndTime() {
        return this.endTime;
    }
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    public float getTimezone() {
        return this.timezone;
    }
    public void setTimezone(float timezone) {
        this.timezone = timezone;
    }
    public long getTotalSteps() {
        return this.totalSteps;
    }
    public void setTotalSteps(long totalSteps) {
        this.totalSteps = totalSteps;
    }
    public float getTotalDistance() {
        return this.totalDistance;
    }
    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }
    public int getTotalCalories() {
        return this.totalCalories;
    }
    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }
    public int getTotalDuration() {
        return this.totalDuration;
    }
    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }
    public int getGoal() {
        return this.goal;
    }
    public void setGoal(int goal) {
        this.goal = goal;
    }
    public String getActionType() {
        return this.actionType;
    }
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    public long getDateHour() {
        return this.dateHour;
    }
    public void setDateHour(long dateHour) {
        this.dateHour = dateHour;
    }
    public long getXinfo1() {
        return this.xinfo1;
    }
    public void setXinfo1(long xinfo1) {
        this.xinfo1 = xinfo1;
    }
    public long getXinfo2() {
        return this.xinfo2;
    }
    public void setXinfo2(long xinfo2) {
        this.xinfo2 = xinfo2;
    }
    public String getXinfo3() {
        return this.xinfo3;
    }
    public void setXinfo3(String xinfo3) {
        this.xinfo3 = xinfo3;
    }
    public String getXinfo4() {
        return this.xinfo4;
    }
    public void setXinfo4(String xinfo4) {
        this.xinfo4 = xinfo4;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }


}
