package com.github.slidedrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.video.VideoActivity;
import com.tcl.token.javaToken.BASE64_src.sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        EventBus.getDefault().register(this);
    }

    private void initView() {
        imageView = findViewById(R.id.img);
    }

    public void customActivity(View view) {
        goToNextActivity(CustomActivity.class);
    }

    public void swipeActivity(View view) {
        goToNextActivity(SwipeActivity.class);
    }

    public void videoActivity(View view) {
        goToNextActivity(VideoActivity.class);
    }

    private void goToNextActivity(Class cls) {
        startActivity(new Intent(this, cls));
    }

    public void greendaoActivity(View view) {
        String ss = "O2SCwpdPxPw/lul/kbXkyvH19EJM6+I3ioygfcQL4L9YmdUFWfg=";
        decryptCFB(ss);
//        login();
//        getTimestamp();
//        updateFile();
//        updateFile();
//        downFile();
//        setUser();
    }

    public void login() {
//        String USERNAME = "161058323@qq.com";
//        String PASSWORD = "acz9bdw4";
//
//        Xhelper.BASE_URL = "https://www.alcatel-move.com";
//        String SECURL = "/v1.0/account/login";
//        Xhelper.KEY = "vEWZapEpW5OezzEs5Su44xAbCiy9-arCJz7eoLJfjac2h1r4VF0";
//        Xhelper.UID = "";
//        Xhelper.ACCESS_TOKEN = "";
//        Xhelper.LANGUAGE = "en";
//        Xhelper.PRINT_TAG = true;
//        Xhelper.PRINT_HEAD = true;
//        // 调用登陆请求
//        LoginParam loginParam = new LoginParam(USERNAME,PASSWORD);
//        Xhelper<Loginbean> xhelper = new Xhelper<Loginbean>().xUrl(SECURL);
//        xhelper.xParam(loginParam).xPost(new XNormalCallback<Loginbean>() {
//            @Override
//            public void successByValue(Loginbean result) {
//                Log.i(TAG, "successByValue: " + result.toString());
//            }
//
//            @Override
//            public void successNoValue() {
//
//            }
//
//            @Override
//            public void appError(Throwable ex) {
//                Log.i(TAG, "appError: ");
//            }
//
//            @Override
//            public void serverError(ServerError error) {
//                Log.i(TAG, "serverError: " + error.error_field);
//            }
//
//            @Override
//            public void cancel(Callback.CancelledException cex) {
//
//            }
//
//            @Override
//            public void finish() {
//
//            }
//        });

    }

    public void setUser() {
//        Xhelper.BASE_URL = "https://www.alcatel-move.com";
////        String SECURL = "/v1.0/account/15782713702718311309";
////        Xhelper.KEY = "vEWZapEpW5OezzEs5Su44xAbCiy9-arCJz7eoLJfjac2h1r4VF0";
////        Xhelper.UID = "15782713702718311309";
////        Xhelper.ACCESS_TOKEN = "z9Q5hkRL44a_j9mQVyf_YOSMfoFi50HFkVZ7nHR2Dr8otpKopMNZG9dIPiZcN7lNpgYa6TCsCc_pAOYaKfQoDAH8yoU";
////        Xhelper.LANGUAGE = "en";
////        Xhelper.PRINT_TAG = true;
////        UserParam userParam = new UserParam();
////        Xhelper xhelper = new Xhelper().xUrl(SECURL);
////        xhelper.xParam(userParam).xPatch(new XNormalCallback(){
////
////            @Override
////            public void successByValue(Object result) {
////
////            }
////
////            @Override
////            public void successNoValue() {
////                Log.i(TAG, "successNoValue: ");
////            }
////
////            @Override
////            public void appError(Throwable ex) {
////                Log.i(TAG, "appError: ");
////            }
////
////            @Override
////            public void serverError(ServerError error) {
////                Log.i(TAG, "serverError: ");
////            }
////
////            @Override
////            public void cancel(Callback.CancelledException cex) {
////                Log.i(TAG, "cancel: ");
////            }
////
////            @Override
////            public void finish() {
////                Log.i(TAG, "finish: ");
////            }
////        });
    }

//    public void updateFile() {
//        String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "familywatch2"
//                + File.separator + "dd.png";
//        File file = new File(path);
//        if (file.exists()) {
//            Xhelper.BASE_URL = "https://www.alcatel-move.com";
//            String SECURL = "/v1.0/fs";
//            Xhelper.KEY = "vEWZapEpW5OezzEs5Su44xAbCiy9-arCJz7eoLJfjac2h1r4VF0";
//            Xhelper.UID = "15782713702718311309";
//            Xhelper.ACCESS_TOKEN = "qz-Sv4NiG0WsV1OdQy14HsOXaYKqb9XIArkcEI85bLA3ou0kbUZI4FW3tmyEIXeSK_5aLqZnW66YOw1mwJiG2t7zwbY";
//            Xhelper.LANGUAGE = "en";
//            Xhelper.PRINT_TAG = true;
//            Xhelper xhelper  = new Xhelper();
//            xhelper.uploadImage(file, new XTransferListener() {
//                @Override
//                public void waiting() {
//                    Log.i(TAG, "waiting: ");
//                }
//
//                @Override
//                public void start() {
//                    Log.i(TAG, "start: ");
//                }
//
//                @Override
//                public void loading(long total, long current, boolean isDownloading) {
//                    Log.i(TAG, "loading: ");
//                }
//
//                @Override
//                public void success(String fid) {
//                    Log.i(TAG, "success: ");
//                }
//
//                @Override
//                public void success(File file) {
//                    Log.i(TAG, "success: ");
//                }
//
//                @Override
//                public void appError(Throwable ex) {
//
//                }
//
//                @Override
//                public void serverError(ServerError error) {
//
//                }
//
//                @Override
//                public void cancel(Callback.CancelledException cex) {
//                    Log.i(TAG, "cancel: ");
//                }
//
//
//                @Override
//                public void finish() {
//                    Log.i(TAG, "finish: ");
//                }
//
//                @Override
//                public void kickOff(ServerError var1) {
//
//                }
//            });
//
//        }else {
//            Log.i(TAG, "updateFile: not found file");
//        }
//    }

//    private void downFile() {
//        Xhelper.BASE_URL = "https://www.alcatel-move.com";
////        String SECURL = "/v1.0/fs/49,3e4db69d3fe068";
//        Xhelper.KEY = "vEWZapEpW5OezzEs5Su44xAbCiy9-arCJz7eoLJfjac2h1r4VF0";
//        Xhelper.UID = "15782713702718311309";
//        Xhelper.ACCESS_TOKEN = "kbk_BqVdZpK9LkX6GvkLsNvBfXFa7RyNIl3DrS96DkhQCatt6G3rxaRNAwV76T0vadJF2NPZypIZA6jA9qnTiiAmN08";
//        Xhelper.LANGUAGE = "en";
//        Xhelper.PRINT_TAG = true;
//        Xhelper.PRINT_PROGRESS = true;
//        Xhelper.PRINT_HEAD = true;
//        Xhelper<File> xhelper = new Xhelper<File>();
//        String filePath = Environment.getExternalStorageDirectory() + File.separator + "49,3e4db69d3fe068.png";
//        xhelper.downFile("49,3e4db69d3fe067", filePath, new XTransferListener() {
//            @Override
//            public void waiting() {
//
//            }
//
//            @Override
//            public void start() {
//
//            }
//
//            @Override
//            public void loading(long total, long current, boolean isDownloading) {
//
//            }
//
//            @Override
//            public void success(String fid) {
//
//            }
//
//            @Override
//            public void success(File file) {
//                Log.i(TAG, "success: " + file.getPath());
//            }
//
//            @Override
//            public void appError(Throwable ex) {
//
//            }
//
//            @Override
//            public void serverError(ServerError error) {
//
//            }
//
//            @Override
//            public void cancel(Callback.CancelledException cex) {
//
//            }
//            @Override
//            public void finish() {
//
//            }
//
//            @Override
//            public void kickOff(ServerError var1) {
//
//            }
//        });
//    }

//    private void getTimestamp() {
//        Xhelper.BASE_URL = "https://www.alcatel-move.com";
//        String SECURL = "/v1.0/im/timestamp";
//        Xhelper.KEY = "vEWZapEpW5OezzEs5Su44xAbCiy9-arCJz7eoLJfjac2h1r4VF0";
//        Xhelper.UID = "15782713702718311309";
//        Xhelper.ACCESS_TOKEN = "gM5fF2K1Hhtn_t5q1pAqftZfvjgbRrpAO7VRpXayP7zDyQz9cLxyG2NpNGrF_F_jcIJjTQqsytMcq0N7YvuDqoTZy1E";
//        Xhelper.LANGUAGE = "en";
//        Xhelper.PRINT_TAG = true;
//        UserParam userParam = new UserParam();
//        Xhelper<TimeResponse> xhelper = new Xhelper().xUrl(SECURL);
//        xhelper.xGet(new XNormalCallback<TimeResponse>() {
//            @Override
//            public void successByValue(TimeResponse result) {
//                Log.i(TAG, "successByValue: " + result.getTimestamp());
//            }
//
//            @Override
//            public void successNoValue() {
//                Log.i(TAG, "successNoValue: ");
//            }
//
//            @Override
//            public void appError(Throwable ex) {
//                Log.i(TAG, "appError: " + ex.getMessage());
//            }
//
//            @Override
//            public void serverError(ServerError error) {
//                Log.i(TAG, "serverError: " + error.getError_msg());
//            }
//
//            @Override
//            public void cancel(Callback.CancelledException cex) {
//                Log.i(TAG, "cancel: ");
//            }
//
//            @Override
//            public void finish() {
//                Log.i(TAG, "finish: ");
//            }
//        });
//    }

//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void kickOff(KickOffBean kickbean) {
//        Log.i(TAG, "kickOff: ");
//    }


    public void decryptCFB(String sSrc) {
        try {
            byte[] encrypted = new BASE64Decoder().decodeBuffer(sSrc);
            byte[] key = new byte[]{(byte) 0x86, (byte) 0x8e, (byte) 0xea, (byte) 0x52, (byte) 0xa1, (byte) 0xe4, (byte) 0x65, (byte) 0xb0,
                    (byte) 0xb8, (byte) 0x4a, (byte) 0xcd, (byte) 0xb7, (byte) 0x08, (byte) 0x64, (byte) 0xa1, (byte) 0x5c};
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
            byte[] ivBytes = new byte[16];
            for (int i = 0; i < 16; i++) {
                ivBytes[i] = encrypted[i];
            }
            byte[] dataBytes = new byte[22];
            for (int i = 0; i < 22; i++) {
                dataBytes[i] = encrypted[16 + i];
            }
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(dataBytes);

            byte[] pidbytes = new byte[2];
            for (int i = 0; i < 2; i++) {
                pidbytes[i] = original[i];
            }
            Log.i(TAG, "decryptCFB: " + bytes2Int(pidbytes));

            byte[] deviceBytes = new byte[16];
            int j = 0;
            for (int i = 0; i < 16; i++) {
                if (original[i+2] == 0){
                    break;
                }
                Log.i(TAG, "decryptCFB: " + original[i+2]);
                deviceBytes[i] = original[i + 2];
                j++;
            }
            String deviceId =  new String(deviceBytes,0,j);
            Log.i(TAG, "decryptCFB: " + deviceId);

            byte[] timeBytes = new byte[4];
            for (int i = 0; i < 4; i++) {
                timeBytes[i] = original[i + 18];
            }
            Log.i(TAG, "decryptCFB: " + bytes2Long(timeBytes));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int bytes2Int(byte[] bytes) {
        int num = bytes[1] & 0xFF;
        num |= ((bytes[0] << 8) & 0xFF00);
        return num;
    }


    public static long bytes2Long(byte[] bytes) {
        long num = bytes[3] & 0xFF;
        num |= ((bytes[2] << 8) & 0xFF00);
        num |= ((bytes[1] << 16) & 0xFF0000);
        num |= ((bytes[0] << 24) & 0xFF000000);
        return num;
    }
}
