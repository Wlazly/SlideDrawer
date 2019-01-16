package com.github.greendao.bean.device;

import java.util.List;

/**
 * Created by wzhiqiang on 2019/1/11.
 */

public class DeviceSettingsBean {

    public String period;
    public boolean alarm;
    public boolean anti_loss;
    public int anti_loss_length;
    public boolean auto_answer;
    public boolean auto_power_onoff;
    public boolean auto_time;
    public long boot_time;
    public boolean calender;
    public boolean cameraControl;
    public boolean climbing;
    public boolean comunicationIncomingCalls;
    public boolean comunicationMsessages;
    public boolean comunicationNotifyFromVIPS;
    public boolean comunicationNotifyOnWatch;
    public boolean dailyGoalReminder;
    public String device_id;
    public boolean dst;
    public int error_id;
    public boolean flipToMute;
    public boolean flipToMuteCalender;
    public boolean flipToMuteCalls;
    public boolean hour24;
    public boolean inactivity;
    public int inactivity_frequency;
    public boolean incomingCall;
    public boolean musicControl;
    public boolean mute;
    public boolean nfc;
    public boolean not_disturb;
    public boolean notification;
    public ReminderBean reminder;
    public boolean roaming;
    public boolean save_power;
    public List<String> languages;
    public String language;
    public SchoolTimeBean school_time;
    public int screen_timeout;
    public long shutdown_time;
    public boolean syncData;
    public boolean syncDataWifiOnly;
    public int temperatureUnit;
    public String timezone;
    public boolean vibrate;
    public boolean videoControl;
    public boolean watchScreenWakeUp;
    public List<Integer> permissions;
    public List<String> sos;//急救号码
    public List<String> mode; // 定位模式(accurate, normal, savepower)
    public boolean auto_position;//是否自动定位


    public DeviceSettingsBean() {}

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    public boolean isAnti_loss() {
        return anti_loss;
    }

    public void setAnti_loss(boolean anti_loss) {
        this.anti_loss = anti_loss;
    }

    public int getAnti_loss_length() {
        return anti_loss_length;
    }

    public void setAnti_loss_length(int anti_loss_length) {
        this.anti_loss_length = anti_loss_length;
    }

    public boolean isAuto_answer() {
        return auto_answer;
    }

    public void setAuto_answer(boolean auto_answer) {
        this.auto_answer = auto_answer;
    }

    public boolean isAuto_power_onoff() {
        return auto_power_onoff;
    }

    public void setAuto_power_onoff(boolean auto_power_onoff) {
        this.auto_power_onoff = auto_power_onoff;
    }

    public boolean isAuto_time() {
        return auto_time;
    }

    public void setAuto_time(boolean auto_time) {
        this.auto_time = auto_time;
    }

    public long getBoot_time() {
        return boot_time;
    }

    public void setBoot_time(long boot_time) {
        this.boot_time = boot_time;
    }

    public boolean isCalender() {
        return calender;
    }

    public void setCalender(boolean calender) {
        this.calender = calender;
    }

    public boolean isCameraControl() {
        return cameraControl;
    }

    public void setCameraControl(boolean cameraControl) {
        this.cameraControl = cameraControl;
    }

    public boolean isClimbing() {
        return climbing;
    }

    public void setClimbing(boolean climbing) {
        this.climbing = climbing;
    }

    public boolean isComunicationIncomingCalls() {
        return comunicationIncomingCalls;
    }

    public void setComunicationIncomingCalls(boolean comunicationIncomingCalls) {
        this.comunicationIncomingCalls = comunicationIncomingCalls;
    }

    public boolean isComunicationMsessages() {
        return comunicationMsessages;
    }

    public void setComunicationMsessages(boolean comunicationMsessages) {
        this.comunicationMsessages = comunicationMsessages;
    }

    public boolean isComunicationNotifyFromVIPS() {
        return comunicationNotifyFromVIPS;
    }

    public void setComunicationNotifyFromVIPS(boolean comunicationNotifyFromVIPS) {
        this.comunicationNotifyFromVIPS = comunicationNotifyFromVIPS;
    }

    public boolean isComunicationNotifyOnWatch() {
        return comunicationNotifyOnWatch;
    }

    public void setComunicationNotifyOnWatch(boolean comunicationNotifyOnWatch) {
        this.comunicationNotifyOnWatch = comunicationNotifyOnWatch;
    }

    public boolean isDailyGoalReminder() {
        return dailyGoalReminder;
    }

    public void setDailyGoalReminder(boolean dailyGoalReminder) {
        this.dailyGoalReminder = dailyGoalReminder;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public boolean isDst() {
        return dst;
    }

    public void setDst(boolean dst) {
        this.dst = dst;
    }

    public int getError_id() {
        return error_id;
    }

    public void setError_id(int error_id) {
        this.error_id = error_id;
    }

    public boolean isFlipToMute() {
        return flipToMute;
    }

    public void setFlipToMute(boolean flipToMute) {
        this.flipToMute = flipToMute;
    }

    public boolean isFlipToMuteCalender() {
        return flipToMuteCalender;
    }

    public void setFlipToMuteCalender(boolean flipToMuteCalender) {
        this.flipToMuteCalender = flipToMuteCalender;
    }

    public boolean isFlipToMuteCalls() {
        return flipToMuteCalls;
    }

    public void setFlipToMuteCalls(boolean flipToMuteCalls) {
        this.flipToMuteCalls = flipToMuteCalls;
    }

    public boolean isHour24() {
        return hour24;
    }

    public void setHour24(boolean hour24) {
        this.hour24 = hour24;
    }

    public boolean isInactivity() {
        return inactivity;
    }

    public void setInactivity(boolean inactivity) {
        this.inactivity = inactivity;
    }

    public int getInactivity_frequency() {
        return inactivity_frequency;
    }

    public void setInactivity_frequency(int inactivity_frequency) {
        this.inactivity_frequency = inactivity_frequency;
    }

    public boolean isIncomingCall() {
        return incomingCall;
    }

    public void setIncomingCall(boolean incomingCall) {
        this.incomingCall = incomingCall;
    }

    public boolean isMusicControl() {
        return musicControl;
    }

    public void setMusicControl(boolean musicControl) {
        this.musicControl = musicControl;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public boolean isNfc() {
        return nfc;
    }

    public void setNfc(boolean nfc) {
        this.nfc = nfc;
    }

    public boolean isNot_disturb() {
        return not_disturb;
    }

    public void setNot_disturb(boolean not_disturb) {
        this.not_disturb = not_disturb;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public ReminderBean getReminder() {
        return reminder;
    }

    public void setReminder(ReminderBean reminder) {
        this.reminder = reminder;
    }

    public boolean isRoaming() {
        return roaming;
    }

    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }

    public boolean isSave_power() {
        return save_power;
    }

    public void setSave_power(boolean save_power) {
        this.save_power = save_power;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public SchoolTimeBean getSchool_time() {
        return school_time;
    }

    public void setSchool_time(SchoolTimeBean school_time) {
        this.school_time = school_time;
    }

    public int getScreen_timeout() {
        return screen_timeout;
    }

    public void setScreen_timeout(int screen_timeout) {
        this.screen_timeout = screen_timeout;
    }

    public long getShutdown_time() {
        return shutdown_time;
    }

    public void setShutdown_time(long shutdown_time) {
        this.shutdown_time = shutdown_time;
    }

    public boolean isSyncData() {
        return syncData;
    }

    public void setSyncData(boolean syncData) {
        this.syncData = syncData;
    }

    public boolean isSyncDataWifiOnly() {
        return syncDataWifiOnly;
    }

    public void setSyncDataWifiOnly(boolean syncDataWifiOnly) {
        this.syncDataWifiOnly = syncDataWifiOnly;
    }

    public int getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(int temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public boolean isVideoControl() {
        return videoControl;
    }

    public void setVideoControl(boolean videoControl) {
        this.videoControl = videoControl;
    }

    public boolean isWatchScreenWakeUp() {
        return watchScreenWakeUp;
    }

    public void setWatchScreenWakeUp(boolean watchScreenWakeUp) {
        this.watchScreenWakeUp = watchScreenWakeUp;
    }

    public List<Integer> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Integer> permissions) {
        this.permissions = permissions;
    }

    public List<String> getSos() {
        return sos;
    }

    public void setSos(List<String> sos) {
        this.sos = sos;
    }

    public List<String> getMode() {
        return mode;
    }

    public void setMode(List<String> mode) {
        this.mode = mode;
    }

    public boolean isAuto_position() {
        return auto_position;
    }

    public void setAuto_position(boolean auto_position) {
        this.auto_position = auto_position;
    }
}
