package com.github.slidedrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){

    }

    public void customActivity(View view) {
        goToNextActivity(CustomActivity.class);
    }

    public void swipeActivity(View view) {
        goToNextActivity(SwipeActivity.class);
    }

    public void videoActivity(View view){
        Intent intent = new Intent("video");
        startActivity(intent);
//        goToNextActivity(VideoActivity.class);
    }

    private void goToNextActivity(Class cls){
        startActivity(new Intent(this,cls));
    }

    public void greendaoActivity(View view) {
//        goToNextActivity(GreenDaoActivity.class);
    }
}
