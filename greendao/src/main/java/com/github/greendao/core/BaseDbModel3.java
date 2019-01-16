package com.github.greendao.core;


import com.github.greendao.bean.device.DaoSession;
import com.github.greendao.core.dbCallback.InsertCallBack;
import com.github.greendao.core.dbCallback.InsertOrUpdateCallBack;
import com.github.greendao.core.dbCallback.QueryListCallBack;
import com.github.greendao.core.dbCallback.UpdateCallBack;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wzhiqiang on 2019/1/3.
 */

/**
 *
 * @param <T> T代表你的entity实体类
 * @param <K> K代表你的主键
 */
public abstract class BaseDbModel3<T,K> {

    private static DaoSession mDaoSessing;

    public BaseDbModel3(){
        mDaoSessing = DbHelper.getInstance().getDaoSession();
    }

    /**
     * 获取Dao 来进行更加高级的插入，查询，删除，更新等操作
     * @param entityClass
     * @param <J>
     * @return
     */
    protected  <J extends AbstractDao<T,K>> J getDao(Class<? extends Object> entityClass) {
        return (J) mDaoSessing.getDao(entityClass);
    }

    protected   <J extends AbstractDao<T,K>> J getDao() {
        return getDao(getClass());
    }

    private Class<T> toClass() {
        /* **** 注意这里一定要使用监听器去获取实现类的泛型 **** */
        Type genericInterfaces =getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genericInterfaces).getActualTypeArguments();
        Type generic = params[0];
        Class<T> mTClass = (Class<T>) generic;
        return mTClass;
    }


    protected void insert(T item) {
        mDaoSessing.insert(item);
    }

    /**
     * 同步插入
     * @param items
     */
    protected void insert(List<T> items) {
        mDaoSessing.runInTx(() -> {
                for (T item : items) {
                    mDaoSessing.insert(item);
                }
            }
        );
    }

    /**
     * 异步插入
     * @param items
     * @param callBack
     */
    protected void insertAsnyc(List<T> items, InsertCallBack callBack) {
        mDaoSessing.startAsyncSession().runInTx(() -> {
            for (T item : items) {
                mDaoSessing.insert(item);
            }
            callBack.insertSuccess();
        });
    }

    protected void insertOrUpdate(T item) {
        mDaoSessing.insertOrReplace(item);
    }

    protected void insertOrUpdate(List<T> items) {
        mDaoSessing.runInTx(() -> {
            for (T item : items) {
                mDaoSessing.insertOrReplace(item);
            }
        });
    }

    /**
     * 异步插入或更新数据
     * @param items
     * @param callBack
     */
    protected void insertOrUpdateAsnyc(List<T> items, InsertOrUpdateCallBack callBack) {
        mDaoSessing.startAsyncSession().runInTx(() -> {
            for (T item : items) {
                mDaoSessing.insertOrReplace(item);
            }
            callBack.insertOrUpdateSuccess();
        });
    }

    protected void deleteByKey(K key) {
        getDao(toClass()).deleteByKey(key);
    }

    protected void delete(T item) {
        mDaoSessing.delete(item);
    }

    protected void delete(List<T> items) {
        mDaoSessing.runInTx(() -> {
            for (T item : items) {
                mDaoSessing.delete(item);
            }
        });
    }

    /**
     * 清空表中的所有数据
     * @param entityClass
     */
    protected void deleteAll(Class entityClass) {
        mDaoSessing.deleteAll(entityClass);
    }

    protected void update(T item) {
        mDaoSessing.update(item);
    }

    /**
     * 更新entity 集合
     * @param items
     */
    protected void update(List<T> items) {
        mDaoSessing.runInTx(() -> {
            for (T item : items) {
                mDaoSessing.update(item);
            }
        });
    }

    /**
     * 异步更新
     * @param items
     * @param callBack
     */
    protected void updateAsync(List<T> items, UpdateCallBack callBack) {
        mDaoSessing.startAsyncSession().runInTx(() -> {
            for (T item : items) {
                mDaoSessing.update(item);
            }
            callBack.updateSuccess();
        });
    }

    /**
     * 查询单个entity
     * @param key 主键
     * @return
     */
    protected T query(K key) {
        return getDao(toClass()).load(key);
    }

    /**
     * 同步查询
     * @return
     */
    protected  List<T> queryAll() {
        DbHelper.getInstance().clearDaoSessing(getDao(toClass()));
        return getDao(toClass()).loadAll();
    }

    /**
     * 异步查询
     * @param callBack
     */
    protected void queryAllAsync(QueryListCallBack<T> callBack) {

        mDaoSessing.startAsyncSession().runInTx(() -> {
            List<T> entityList = getDao(toClass()).loadAll();
//            callBack.querySuccess(entityList);
            callBack.querySuccess(entityList);
        });
    }


    /**
     * 查询多个数据
     * @param whereConditions
     * @return
     */
    protected T querySingleBean(WhereCondition[] whereConditions){
        DbHelper.getInstance().clearDaoSessing(getDao());
        QueryBuilder<T> tQueryBuilder = getDao().queryBuilder();
        for (WhereCondition whereCondition : whereConditions) {
            tQueryBuilder.where(whereCondition);
        }
        return tQueryBuilder.limit(1).unique();
    }

    protected List<T> queryListBean(WhereCondition[] whereConditions){
        DbHelper.getInstance().clearDaoSessing(getDao());
        QueryBuilder<T> tQueryBuilder = getDao().queryBuilder();
        for (WhereCondition whereCondition : whereConditions) {
            tQueryBuilder.where(whereCondition);
        }
        return tQueryBuilder.list();
    }


    /**
     * 获取entity在数据库中的条数
     * @return
     */
    protected long getCount() {
        return getDao(toClass()).count();
    }
}
