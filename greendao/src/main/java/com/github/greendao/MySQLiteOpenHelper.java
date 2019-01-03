package com.github.greendao;

import android.content.Context;

import com.github.greendao.greendao.DaoMaster;
import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;

/**
 * Created by wzhiqiang on 2018/12/26.
 */

public class MySQLiteOpenHelper extends DaoMaster.DevOpenHelper{

   private Class<? extends AbstractDao<?,?>> classes[];
    public MySQLiteOpenHelper(Context context, String name,Class<? extends AbstractDao<?,?>> classes[]) {
        super(context, name);
        this.classes = classes;
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
//        super.onUpgrade(db, oldVersion, newVersion);
        if (classes == null || classes.length == 0){
            super.onUpgrade(db,oldVersion,newVersion);
        }else {
            MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
                @Override
                public void onCreateAllTables(Database db, boolean ifNotExists) {
                    DaoMaster.createAllTables(db, ifNotExists);
                }
                @Override
                public void onDropAllTables(Database db, boolean ifExists) {
                    DaoMaster.dropAllTables(db, ifExists);
                }
            },classes);
        }

    }
}
