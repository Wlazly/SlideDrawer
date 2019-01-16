package com.github.greendao.core;

import com.github.greendao.bean.device.DaoSession;
import com.github.greendao.core.dbCallback.InsertCallBack;
import com.github.greendao.core.dbCallback.InsertOrUpdateCallBack;
import com.github.greendao.core.dbCallback.QueryListCallBack;
import com.github.greendao.core.dbCallback.UpdateCallBack;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by wzhiqiang on 2019/1/15.
 */

public class BaseDbModel {

    public static final int ASC = 1;
    public static final int DESC = 2;

    private static DaoSession getDaosession(){
        return DbHelper.getInstance().getDaoSession();
    }

    /**
     * 获取Dao 来进行更加高级的插入，查询，删除，更新等操作
     * @param entityClass
     * @param <J>
     * @return
     */
    protected  static <J extends AbstractDao> J getDao(Class<? extends Object> entityClass) {
        return (J) getDaosession().getDao(entityClass);
    }

    protected static <T> void insert(T item){
        getDaosession().insert(item);
    }

    /**
     * 同步插入
     * @param items
     */
    protected static <T> void insert(List<T> items) {
        getDaosession().runInTx(() -> {
                    for (T item : items) {
                        getDaosession().insert(item);
                    }
                }
        );
    }

    /**
     * 异步插入
     * @param items
     * @param callBack
     */
    protected static <T> void insertAsnyc(List<T> items, InsertCallBack callBack) {
        getDaosession().startAsyncSession().runInTx(() -> {
            for (T item : items) {
                getDaosession().insert(item);
            }
            if (callBack != null) {
                callBack.insertSuccess();
            }
        });
    }

    protected static <T> void insertOrUpdate(T item) {
        getDaosession().insertOrReplace(item);
    }

    protected static <T> void insertOrUpdate(List<T> items) {
        getDaosession().runInTx(() -> {
            for (T item : items) {
                getDaosession().insertOrReplace(item);
            }
        });
    }

    /**
     * 异步插入或更新数据
     * @param items
     * @param callBack
     */
    protected static <T> void insertOrUpdateAsnyc(List<T> items, InsertOrUpdateCallBack callBack) {
        getDaosession().startAsyncSession().runInTx(() -> {
            for (T item : items) {
                getDaosession().insertOrReplace(item);
            }
            if (callBack != null) {
                callBack.insertOrUpdateSuccess();
            }
        });
    }

    protected static void deleteByKey(Class cls,Long key) {
        getDao(cls).deleteByKey(key);
    }

    protected static <T> void delete(T item) {
        getDaosession().delete(item);
    }

    protected static <T> void delete(List<T> items) {
        getDaosession().runInTx(() -> {
            for (T item : items) {
                getDaosession().delete(item);
            }
        });
    }

    /**
     * 清空表中的所有数据
     * @param entityClass
     */
    protected static void deleteAll(Class entityClass) {
        getDaosession().deleteAll(entityClass);
    }

    protected static <T> void update(T item) {
        getDaosession().update(item);
    }

    /**
     * 更新entity 集合
     * @param items
     */
    protected static <T> void update(List<T> items) {
        getDaosession().runInTx(() -> {
            for (T item : items) {
                getDaosession().update(item);
            }
        });
    }

    /**
     * 异步更新
     * @param items
     * @param callBack
     */
    protected static <T> void updateAsync(List<T> items, UpdateCallBack callBack) {
        getDaosession().startAsyncSession().runInTx(() -> {
            for (T item : items) {
                getDaosession().update(item);
            }
            if (callBack != null) {
                callBack.updateSuccess();
            }
        });
    }

    /**
     * 查询单个entity
     * @param key 主键
     * @return
     */
    protected static <T> T query(Class<T> cls,Long key) {
        DbHelper.getInstance().clearDaoSessing(getDao(cls));
        return (T)(getDao(cls).load(key));
    }

    /**
     * 同步查询
     * @return
     */
    protected  static <T> List<T> queryAll(Class<T> cls) {
        DbHelper.getInstance().clearDaoSessing(getDao(cls));
        return getDao(cls).loadAll();
    }

    /**
     * 异步查询
     * @param callBack
     */
    protected static <T> void queryAllAsync(Class<T>cls,QueryListCallBack<T> callBack) {
        DbHelper.getInstance().clearDaoSessing(getDao(cls));
        getDaosession().startAsyncSession().runInTx(() -> {
            List<T> entityList = getDao(cls).loadAll();
            if (callBack != null){
                callBack.querySuccess(entityList);
            }
        });
    }

    /**
     * 查询多个数据
     * @param whereConditions
     * @return
     */
    protected static <T> T querySingle(Class<T> cls, WhereCondition[] whereConditions){
        DbHelper.getInstance().clearDaoSessing(getDao(cls));
        QueryBuilder<T> tQueryBuilder = getDao(cls).queryBuilder();
        if (whereConditions != null){
            for (WhereCondition whereCondition : whereConditions) {
                tQueryBuilder.where(whereCondition);
            }
        }
        return tQueryBuilder.limit(1).unique();
    }

    /**
     * 根据条件查询数据，条件都是and
     * @param cls
     * @param whereConditions
     * @param <T>
     * @return
     */
    protected static  <T> List<T> queryList(Class<T> cls, WhereCondition[] whereConditions){
        DbHelper.getInstance().clearDaoSessing(getDao(cls));
        QueryBuilder<T> tQueryBuilder = getDao(cls).queryBuilder();
        if (whereConditions != null) {
            for (WhereCondition whereCondition : whereConditions) {
                tQueryBuilder.where(whereCondition);
            }
        }
        return tQueryBuilder.list();
    }

    /**
     * 异步查询,根据条件查询数据，条件都是and
     * @param cls 要查询的数据库类
     * @param whereConditions  条件数组
     * @param callBack  回调
     * @param <T>
     */
    protected  static  <T> void queryListAsync(Class<T> cls, WhereCondition[] whereConditions,QueryListCallBack<T> callBack){
        DbHelper.getInstance().clearDaoSessing(getDao(cls));
        getDaosession().startAsyncSession().runInTx(() -> {
            QueryBuilder<T> tQueryBuilder = getDao(cls).queryBuilder();
            if (whereConditions != null) {
                for (WhereCondition whereCondition : whereConditions) {
                    tQueryBuilder.where(whereCondition);
                }
            }
            if (callBack != null) {
                callBack.querySuccess(tQueryBuilder.list());
            }
        });
    }

    /**
     * 异步查询,根据条件查询数据，条件都是and
     * @param cls 要查询的数据库类
     * @param whereConditions 条件数组
     * @param order
     * @param orderty
     * @param callBack 回调
     * @param <T>
     */
    protected static <T> void queryListAsync(Class<T> cls, WhereCondition[] whereConditions, int order, Property orderty, QueryListCallBack<T> callBack) {
        DbHelper.getInstance().clearDaoSessing(getDao(cls));
        getDaosession().startAsyncSession().runInTx(() -> {
            QueryBuilder<T> queryBuilder = getDao(cls).queryBuilder();
            if (whereConditions != null) {
                for (WhereCondition whereCondition : whereConditions) {
                    queryBuilder.where(whereCondition);
                }
            }
            if (order == ASC){
                queryBuilder.orderAsc(orderty);
            }else if (order == DESC) {
                queryBuilder.orderDesc(orderty);
            }
            if (callBack != null) {
                callBack.querySuccess(queryBuilder.list());
            }
        });
    }

    /**
     * 获取entity在数据库中的条数
     * @return
     */
    protected static <T> long getCount(Class<T> cls) {
        return getDao(cls).count();
    }
}
