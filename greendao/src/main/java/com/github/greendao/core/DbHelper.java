package com.github.greendao.core;

import android.app.Application;
import android.support.annotation.Nullable;

import com.github.greendao.bean.device.DaoMaster;
import com.github.greendao.bean.device.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;

/**
 * Created by wzhiqiang on 2018/12/25.
 */

public class DbHelper {

    private static DbHelper instance;
    private DaoSession mDaoSessing;
    private MySQLiteOpenHelper mOpenHelper;
    private final DaoMaster mDaoMaster;

    /**
     * 最好在Application中初始化
     * @param application
     * @param dataBaseName 数据库名字
     * @param classes 当你修改某个类的成员时，就要传进来该类，如果传为null，代表直接删除之前的数据库表中的数据，重新创建空的表
     * @return
     */
    public static DbHelper init(Application application, String dataBaseName, @Nullable Class<? extends AbstractDao<?, ?>>[] classes) {
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
        mOpenHelper = new MySQLiteOpenHelper(application, dataBaseName, classes);
        Database db = mOpenHelper.getWritableDb();
        mDaoMaster = new DaoMaster(db);
        mDaoSessing = mDaoMaster.newSession();
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
        clearAllDaoSession();
        closeHelper();
    }

    private void closeHelper() {
        if (mOpenHelper != null) {
            mOpenHelper.close();
            mOpenHelper = null;
        }
    }

    /**
     * 清除整个数据库的缓存session，没有缓存对象返回
     */
    public void clearAllDaoSession() {
        mDaoSessing.clear();
    }

    /**
     * 清除单个Dao的identity scope,这样就会清除缓存了
     * @param dao
     */
    public void clearDaoSessing(AbstractDao dao) {
        dao.detachAll();
    }

    /**
     * 删除所有表
     */
    public void deleteAllTable(){
        DaoMaster.dropAllTables(mDaoMaster.getDatabase(),true);
        //必须要重新创建表,不然重新进行数据库操作的时候（crud）会报找不到数据库表的错误：
        DaoMaster.createAllTables(mDaoMaster.getDatabase(),true);
    }

}
