package com.github.slidedrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.customActivity)
    Button mCustomBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mCustomBtn.setText("hello");

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
}
