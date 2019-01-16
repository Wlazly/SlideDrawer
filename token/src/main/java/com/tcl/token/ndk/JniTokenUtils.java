package com.tcl.token.ndk;

import android.util.Log;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

public class JniTokenUtils {
    public static native String getStringFormC();

    public static native byte[] getKeyValue();

    public static native byte[] getIv();

    public static native long getTimeStamp();

    public static native long getRandom();

    public static native int getEncryptInfo(byte[] data, EncryptInfo info);

    private static byte[] keyValue;
    private static byte[] iv;

    private static SecretKey key;
    private static AlgorithmParameterSpec paramSpec;
    private static Cipher ecipher;

    public static class EncryptInfo {
        public String timestamp;
        public String random;
        public String aeskey;
        public String ivkey;
        public String encryptkey;
    }

    static {
        System.loadLibrary("jniToken");    //defaultConfig.ndk.moduleName
    }

    public long getLongTimeStamp() {
        return getTimeStamp();
    }

    public long getLongRandom() {
        return getRandom();
    }

    /**
     * 加密
     **/
    public String encode(String msg) {
        String str = "";
        try {
            //用密钥和一组算法参数初始化此 cipher
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            //加密并转换成16进制字符串
            str = asHex(ecipher.doFinal(msg.getBytes()));
        } catch (BadPaddingException e) {
        } catch (InvalidKeyException e) {
        } catch (InvalidAlgorithmParameterException e) {
        } catch (IllegalBlockSizeException e) {
        }
        return str;
    }

    /**
     * 解密
     **/
    public String decode(String value) {
        try {
            ecipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            return new String(ecipher.doFinal(asBin(value)));
        } catch (BadPaddingException e) {
        } catch (InvalidKeyException e) {
        } catch (InvalidAlgorithmParameterException e) {
        } catch (IllegalBlockSizeException e) {
        }
        return "";
    }

    /**
     * 转16进制
     **/
    private String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)//小于十前面补零
                strbuf.append("0");
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

    /**
     * 转2进制
     **/
    private byte[] asBin(String src) {
        if (src.length() < 1)
            return null;
        byte[] encrypted = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++) {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);//取高位字节
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);//取低位字节
            encrypted[i] = (byte) (high * 16 + low);
        }
        return encrypted;
    }

    public void encryptInfo(byte[] data, EncryptInfo info) {
        getEncryptInfo(data, info);
    }

    public void test() {
        EncryptInfo info = new EncryptInfo();
        byte[] data = {0};
        getEncryptInfo(data, info);
        Log.v("javaside", "javaTimestamp = " + info.timestamp);
    }

    public static EncryptInfo newEncryptInfo() {
        return new EncryptInfo();
    }
}
