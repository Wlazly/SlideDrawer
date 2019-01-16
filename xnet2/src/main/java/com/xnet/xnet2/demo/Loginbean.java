package com.xnet.xnet2.demo;

/*
 * Created by qianli.ma on 2019/1/8 0008.
 */
public class Loginbean {
    
    private String uid;
    private String access_token;
    private long expired_at;
    private String expired_time;
    private int account_expired_time;

    public Loginbean() {
        
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpired_at() {
        return expired_at;
    }

    public void setExpired_at(long expired_at) {
        this.expired_at = expired_at;
    }

    public String getExpired_time() {
        return expired_time;
    }

    public void setExpired_time(String expired_time) {
        this.expired_time = expired_time;
    }

    public int getAccount_expired_time() {
        return account_expired_time;
    }

    public void setAccount_expired_time(int account_expired_time) {
        this.account_expired_time = account_expired_time;
    }

    @Override
    public String toString() {
        return "Loginbean{" +
                "uid='" + uid + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expired_time='" + expired_time + '\'' +
                ", account_expired_time=" + account_expired_time +
                '}';
    }
}
