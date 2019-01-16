package com.tcl.token.ndk;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by wzhiqiang on 2019/1/7.
 */

public class ServerEncrypt {

    private String sign;
    private String timestamp;
    private String newtoken;

    public ServerEncrypt(String uid){
        if (!TextUtils.isEmpty(uid)) {
            JniTokenUtils.EncryptInfo info = JniTokenUtils.newEncryptInfo();
            try {
                byte[] data = uid.getBytes("UTF-8");
                JniTokenUtils.getEncryptInfo(data, info);
                this.sign = info.random;
                this.timestamp = info.timestamp;
                this.newtoken = info.encryptkey;
            } catch (UnsupportedEncodingException e) {}
        }
    }

    public String getSign() {
        return sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getNewtoken() {
        return newtoken;
    }
}
