package com.github.slidedrawer;

import android.app.Application;

import com.github.swiperecyclerlayout.greendao.DaoMaster;
import com.github.swiperecyclerlayout.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;


/**
 * Created by wzhiqiang on 2018/12/19.
 */

public class App extends Application {

    public static final String BASENAME = "mkno";
    @Override
    public void onCreate() {
        super.onCreate();
//        DbHelper.init(this,BASENAME,null);
        initDb1();
        initDb2();
    }

    private void initDb1(){
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "mkn0");
        Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession mDaoSessing = daoMaster.newSession();
    }

    private void initDb2(){
        com.github.video.greendao.DaoMaster.DevOpenHelper openHelper = new com.github.video.greendao.DaoMaster.DevOpenHelper(this, "mt40");
        Database db = openHelper.getWritableDb();
        com.github.video.greendao.DaoMaster daoMaster = new com.github.video.greendao.DaoMaster(db);
        com.github.video.greendao.DaoSession mDaoSessing = daoMaster.newSession();
    }
}
