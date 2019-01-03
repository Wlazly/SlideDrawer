package com.github.slidedrawer;

import android.app.Application;

import com.github.greendao.DbHelper;
import com.github.greendao.Entity.User;

/**
 * Created by wzhiqiang on 2018/12/19.
 */

public class App extends Application {

    public static final String BASENAME = "mkno";
    @Override
    public void onCreate() {
        super.onCreate();
        DbHelper.init(this,BASENAME,new Class[]{User.class});
    }
}
