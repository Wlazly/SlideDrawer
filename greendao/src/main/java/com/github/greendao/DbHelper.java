package com.github.greendao;

import android.app.Application;
import com.github.greendao.greendao.DaoMaster;
import com.github.greendao.greendao.DaoSession;
import org.greenrobot.greendao.database.Database;

/**
 * Created by wzhiqiang on 2018/12/25.
 */

public class DbHelper {

    private static DbHelper instance;
    public static final String BASENAME = "mkno";
    private DaoSession mDaoSessing;

    public static DbHelper init(Application application) {
        if (instance == null) {
            // [1]
            synchronized (DbHelper.class) {
                if (instance == null) {
                    //单例模式之双重检测：线程一在此之前线程二到达了位置[1],如果此处不二次判断，那么线程二执行到这里的时候还会重新new
                    instance = new DbHelper(application);
                }
            }
        }
        return instance;
    }
    private DbHelper(Application application){
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(application,BASENAME);
        Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSessing = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return mDaoSessing;
    }
}
