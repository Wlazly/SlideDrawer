package com.github.slidedrawer;

import android.app.Application;

import org.xutils.x;


/**
 * Created by wzhiqiang on 2018/12/19.
 */

public class App extends Application {

    public static final String BASENAME = "allInOne";
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
//        DbHelper.init(this,BASENAME,null);
    }





}
