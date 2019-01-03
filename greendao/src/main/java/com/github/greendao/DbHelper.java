package com.github.greendao;

import android.app.Application;
import android.support.annotation.Nullable;

import com.github.greendao.greendao.DaoMaster;
import com.github.greendao.greendao.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;

/**
 * Created by wzhiqiang on 2018/12/25.
 */

public class DbHelper {

    private static DbHelper instance;
    public static final String BASENAME = "mkno";
    private DaoSession mDaoSessing;
    private MySQLiteOpenHelper openHelper;
    private final DaoMaster daoMaster;

    public static DbHelper init(Application application,String dataBaseName,@Nullable Class<? extends AbstractDao<?, ?>>[] classes) {
        if (instance == null) {
            // [1]
            synchronized (DbHelper.class) {
                if (instance == null) {
                    //单例模式之双重检测：线程一在此之前线程二到达了位置[1],如果此处不二次判断，那么线程二执行到这里的时候还会重新new
                    instance = new DbHelper(application, dataBaseName,classes);
                }
            }
        }
        return instance;
    }

    private DbHelper(Application application, String dataBaseName,Class<? extends AbstractDao<?, ?>>[] classes) {
        openHelper = new MySQLiteOpenHelper(application, dataBaseName, classes);
        Database db = openHelper.getWritableDb();
        daoMaster = new DaoMaster(db);
        mDaoSessing = daoMaster.newSession();
    }

    public static DbHelper getInstance() {
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null) {
                    throw new UnsupportedOperationException("Didn't finish the " + "DbHelper" + " init");
                }
            }
        }
        return instance;
    }

    public DaoSession getDaoSession() {
        return mDaoSessing;
    }


    public void closeConnection(){
        closeHelper();
        clearDaoSessing();
    }

    private void closeHelper() {
        if (openHelper != null) {
            openHelper.close();
            openHelper = null;
        }
    }

    /*
        清除整个session，没有缓存对象返回
     */
    private void clearDaoSessing() {
        mDaoSessing.clear();
    }

    /*
        清除单个Dao的identity scope,这样就会清除缓存了
     */
    private void clearDaoSessing(AbstractDao dao) {
        dao.detachAll();
    }

    /**
     * 删除所有表
     */
    public void deleteAllTable(){
        DaoMaster.dropAllTables(daoMaster.getDatabase(),true);
        //必须要重新创建表,不然重新进行数据库操作的时候（crud）会报找不到数据库表的错误：
        DaoMaster.createAllTables(daoMaster.getDatabase(),true);
    }

}
