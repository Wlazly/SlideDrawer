package com.github.slidedrawer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.video.VideoActivity;
import com.xnet.xnet2.core.ServerError;
import com.xnet.xnet2.core.Xhelper;
import com.xnet.xnet2.demo.LoginParam;
import com.xnet.xnet2.demo.Loginbean;
import com.xnet.xnet2.listener.XNormalListener;
import com.xnet.xnet2.listener.XTransferListener;

import org.xutils.common.Callback;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        imageView = findViewById(R.id.img);
    }

    public void customActivity(View view) {
        goToNextActivity(CustomActivity.class);
    }

    public void swipeActivity(View view) {
        goToNextActivity(SwipeActivity.class);
    }

    public void videoActivity(View view){
        goToNextActivity(VideoActivity.class);
    }

    private void goToNextActivity(Class cls){
        startActivity(new Intent(this,cls));
    }

    public void greendaoActivity(View view) {
//        login();
//        updateFile();
//        updateFile();
//        downFile();
//        goToNextActivity(GreenDaoActivity.class);
    }

    public void login() {
        String USERNAME = "161058323@qq.com";
        String PASSWORD = "acz9bdw5";

        Xhelper.BASE_URL = "https://www.alcatel-move.com";
        String SECURL = "/v1.0/account/login";
        Xhelper.KEY = "vEWZapEpW5OezzEs5Su44xAbCiy9-arCJz7eoLJfjac2h1r4VF0";
        Xhelper.UID = "";
        Xhelper.ACCESS_TOKEN = "";
        Xhelper.LANGUAGE = "en";
        Xhelper.PRINT_TAG = true;
        Xhelper.PRINT_HEAD = true;
        // 调用登陆请求
        LoginParam loginParam = new LoginParam(USERNAME,PASSWORD);
        Xhelper<Loginbean> xhelper = new Xhelper<Loginbean>().xUrl(SECURL);
        xhelper.xParam(loginParam).xPost(new XNormalListener<Loginbean>() {
            @Override
            public void successByValue(Loginbean result) {
                Log.i(TAG, "successByValue: " + result.toString());
            }

            @Override
            public void successNoValue() {

            }

            @Override
            public void appError(Throwable ex) {

            }

            @Override
            public void serverError(ServerError error) {

            }

            @Override
            public void cancel(Callback.CancelledException cex) {

            }

            @Override
            public void finish() {

            }
        });

    }

    public void setUser() {
        Xhelper.BASE_URL = "https://www.alcatel-move.com";
        String SECURL = "/v1.0/account/15782713702718311309";
        Xhelper.KEY = "vEWZapEpW5OezzEs5Su44xAbCiy9-arCJz7eoLJfjac2h1r4VF0";
        Xhelper.UID = "15782713702718311309";
        Xhelper.ACCESS_TOKEN = "qz-Sv4NiG0WsV1OdQy14HsOXaYKqb9XIArkcEI85bLA3ou0kbUZI4FW3tmyEIXeSK_5aLqZnW66YOw1mwJiG2t7zwbY";
        Xhelper.LANGUAGE = "en";
        Xhelper.PRINT_TAG = true;
        UserParam userParam = new UserParam();
        Xhelper xhelper = new Xhelper().xUrl(SECURL);
        xhelper.xParam(userParam).xPatch(new XNormalListener(){

            @Override
            public void successByValue(Object result) {

            }

            @Override
            public void successNoValue() {
                Log.i(TAG, "successNoValue: ");
            }

            @Override
            public void appError(Throwable ex) {
                Log.i(TAG, "appError: ");
            }

            @Override
            public void serverError(ServerError error) {
                Log.i(TAG, "serverError: ");
            }

            @Override
            public void cancel(Callback.CancelledException cex) {
                Log.i(TAG, "cancel: ");
            }

            @Override
            public void finish() {
                Log.i(TAG, "finish: ");
            }
        });
    }

    public void updateFile() {
        String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "familywatch2"
                + File.separator + "dd.png";
        File file = new File(path);
        if (file.exists()) {
            Xhelper.BASE_URL = "https://www.alcatel-move.com";
            String SECURL = "/v1.0/fs";
            Xhelper.KEY = "vEWZapEpW5OezzEs5Su44xAbCiy9-arCJz7eoLJfjac2h1r4VF0";
            Xhelper.UID = "15782713702718311309";
            Xhelper.ACCESS_TOKEN = "qz-Sv4NiG0WsV1OdQy14HsOXaYKqb9XIArkcEI85bLA3ou0kbUZI4FW3tmyEIXeSK_5aLqZnW66YOw1mwJiG2t7zwbY";
            Xhelper.LANGUAGE = "en";
            Xhelper.PRINT_TAG = true;
            Xhelper xhelper  = new Xhelper();
            xhelper.uploadImage(file, new XTransferListener() {
                @Override
                public void waiting() {
                    Log.i(TAG, "waiting: ");
                }

                @Override
                public void start() {
                    Log.i(TAG, "start: ");
                }

                @Override
                public void loading(long total, long current, boolean isDownloading) {
                    Log.i(TAG, "loading: ");
                }   

                @Override
                public void success(String fid) {
                    Log.i(TAG, "success: ");
                }

                @Override
                public void success(File file) {
                    Log.i(TAG, "success: ");
                }

                @Override
                public void appError(Throwable ex) {

                }

                @Override
                public void serverError(ServerError error) {

                }

                @Override
                public void cancel(Callback.CancelledException cex) {
                    Log.i(TAG, "cancel: ");
                }

                @Override
                public void finish() {
                    Log.i(TAG, "finish: ");
                }
            });

        }else {
            Log.i(TAG, "updateFile: not found file");
        }
    }

    private void downFile() {
        Xhelper.BASE_URL = "https://www.alcatel-move.com";
//        String SECURL = "/v1.0/fs/49,3e4db69d3fe068";
        Xhelper.KEY = "vEWZapEpW5OezzEs5Su44xAbCiy9-arCJz7eoLJfjac2h1r4VF0";
        Xhelper.UID = "15782713702718311309";
        Xhelper.ACCESS_TOKEN = "kbk_BqVdZpK9LkX6GvkLsNvBfXFa7RyNIl3DrS96DkhQCatt6G3rxaRNAwV76T0vadJF2NPZypIZA6jA9qnTiiAmN08";
        Xhelper.LANGUAGE = "en";
        Xhelper.PRINT_TAG = true;
        Xhelper.PRINT_PROGRESS = true;
        Xhelper.PRINT_HEAD = true;
        Xhelper<File> xhelper = new Xhelper<File>();
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "49,3e4db69d3fe068.png";
        xhelper.downFile("49,3e4db69d3fe067", filePath, new XTransferListener() {
            @Override
            public void waiting() {

            }

            @Override
            public void start() {

            }

            @Override
            public void loading(long total, long current, boolean isDownloading) {

            }

            @Override
            public void success(String fid) {

            }

            @Override
            public void success(File file) {
                Log.i(TAG, "success: " + file.getPath());
            }

            @Override
            public void appError(Throwable ex) {

            }

            @Override
            public void serverError(ServerError error) {

            }

            @Override
            public void cancel(Callback.CancelledException cex) {

            }

            @Override
            public void finish() {

            }
        });
    }

}
