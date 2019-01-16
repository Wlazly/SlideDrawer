package com.tcl.token.javaToken;


import com.tcl.token.javaToken.BASE64_src.sun.misc.BASE64Decoder;
import com.tcl.token.javaToken.BASE64_src.sun.misc.BASE64Encoder;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AES128 {

//    private String sKey = "0123456789abcdef";
//    private String ivParameter = "0123456789abcdef";

    private byte[] byteKey = new byte[16];
    private byte[] byteIv = new byte[16];

    private static AES128 instance = null;

    private AES128() {

    }

    public static AES128 getInstance() {
        if (instance == null)
            instance = new AES128();
        return instance;
    }

    public void setKey(byte[] key) {
        this.byteKey = key;
    }

    public void setIvParameter(byte[] iv) {
        this.byteIv = iv;
    }

    public byte[] string2ByteWithSplit(String str, String split) {
        byte[] byteArray = new byte[16];
        String[] strArray = str.split(split);
        for (int n = 0; n < strArray.length; n++) {
            byteArray[n] = Byte.valueOf(strArray[n]);
        }
        return byteArray;
    }


    public String encrypt(String sSrc) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        SecretKeySpec skeySpec = new SecretKeySpec(byteKey, "AES");

        IvParameterSpec iv = new IvParameterSpec(byteIv);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new BASE64Encoder().encode(encrypted);
    }

    public String encrypt(String sSrc, byte[] byteKey, byte[] byteIV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        SecretKeySpec skeySpec = new SecretKeySpec(byteKey, "AES");

        IvParameterSpec iv = new IvParameterSpec(byteIV);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

//        for (int n = 0; n < encrypted.length; n++) {
//            Log.v("java byte", "java byte " + n +  "= " + encrypted[n]);
//        }

        return new BASE64Encoder().encode(encrypted);
    }

    public String decrypt(String sSrc) {
        try {
            byte[] raw = byteKey;
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(byteIv);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);

            byte[] original = cipher.doFinal(encrypted1);

            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public String decrypt(String sSrc, byte[] byteKey, byte[] byteIV) throws Exception {
        try {
            byte[] raw = byteKey;
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(byteIV);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);

            byte[] original = cipher.doFinal(encrypted1);

            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String toHexString1(byte[] b) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }

    public static String toHexString1(byte b) {
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1) {
            return "0" + s;
        } else {
            return s;
        }
    }


    public String getTimeStamp() {
        String timeStamp = null;

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        timeStamp = sdf.format(date);

        if (null != timeStamp) {
            return timeStamp;
        }

        return null;
    }

    public static String encryptAesKey(String timestamp) {
        byte[] aesKey = new byte[16];

        // test
        timestamp = "1470879610";

        int len = timestamp.length();


        // get timestamp
        byte[] bTimeStamp = timestamp.getBytes();

        //pkcs5Padding
        for (int n = 0; n < 16; n++) {
            if (n <= len) {
                aesKey[n] = bTimeStamp[n];
            } else {
                aesKey[n] = 0x06;
            }
        }

        if (len < 16) {
            for (int n = len; n < 16; n++) {
                bTimeStamp[n] = 0x06;
            }
        }

        if (len > 16) {

        }

        return null;
    }

    public static void main_test(String[] args) throws Exception {

        String cSrc = "http://www.baidu.com";
        System.out.println(cSrc);

        long lStart = System.currentTimeMillis();
        String enString = AES128.getInstance().encrypt(cSrc);
        System.out.println("Encrypted：" + enString);

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("Encrypt time：" + lUseTime + "ms");

        lStart = System.currentTimeMillis();
        String DeString = AES128.getInstance().decrypt(enString);
        System.out.println("Decrypted：" + DeString);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("Decrypt time：" + lUseTime + "ms");
    }
}
