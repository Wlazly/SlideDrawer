package com.github.greendao.module;

import com.github.greendao.DbHelper;
import com.github.greendao.callback.InsertCallBack;
import com.github.greendao.callback.InsertOrUpdateCallBack;
import com.github.greendao.callback.QueryListCallBack;
import com.github.greendao.callback.UpdateCallBack;
import com.github.greendao.greendao.DaoSession;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

/**
 * Created by wzhiqiang on 2019/1/3.
 */

/**
 *
 * @param <T> T代表你的entity实体类
 * @param <K> K代表你的主键
 */
public class BaseDbModel<T,K> {

    private DaoSession mDaoSessing;

    public BaseDbModel(){
        mDaoSessing = DbHelper.getInstance().getDaoSession();
    }

    /**
     * 获取Dao 来进行更加高级的插入，查询，删除，更新等操作
     * @param entityClass
     * @param <J>
     * @return
     */
    public <J extends AbstractDao<T,K>> J getDao(Class<? extends Object> entityClass) {
        return (J) mDaoSessing.getDao(entityClass);
    }

    public  void insert(T item) {
        mDaoSessing.insert(item);
    }

    /**
     * 同步插入
     * @param items
     */
    public void insert(List<T> items) {
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
    public void insertAsnyc(List<T> items, InsertCallBack callBack) {
        mDaoSessing.startAsyncSession().runInTx(() -> {
            for (T item : items) {
                mDaoSessing.insert(item);
            }
            callBack.insertSuccess();
        });
    }

    public void insertOrUpdate(T item) {
        mDaoSessing.insertOrReplace(item);
    }

    public void insertOrUpdate(List<T> items) {
        mDaoSessing.runInTx(() -> {
            for (T item : items) {
                mDaoSessing.insertOrReplace(item);
            }
        });
    }

    public void insertOrUpdateAsnyc(List<T> items, InsertOrUpdateCallBack callBack) {
        mDaoSessing.startAsyncSession().runInTx(() -> {
            for (T item : items) {
                mDaoSessing.insertOrReplace(item);
            }
            callBack.insertOrUpdateSuccess();
        });
    }

    public void deleteByKey(K key,Class entityClass) {
        getDao(entityClass).deleteByKey(key);
    }

    public void delete(T item) {
        mDaoSessing.delete(item);
    }

    public void delete(List<T> items) {
        mDaoSessing.runInTx(() -> {
            for (T item : items) {
                mDaoSessing.delete(item);
            }
        });
    }

    public void deleteAll(Class entityClass) {
        mDaoSessing.deleteAll(entityClass);
    }

    public void update(T item) {
        mDaoSessing.update(item);
    }

    /**
     * 更新entity 集合
     * @param items
     */
    public void update(List<T> items) {
        mDaoSessing.runInTx(() -> {
            for (T item : items) {
                mDaoSessing.update(item);
            }
        });
    }

    public void update(List<T> items, UpdateCallBack callBack) {
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
     * @param entityClass entity的Class
     * @return
     */
    public T query(K key,Class<T> entityClass) {
        return getDao(entityClass).load(key);
    }

    /**
     * 同步查询
     * @param entityClass
     * @return
     */
    public  List<T> queryAll(Class<T> entityClass) {
        return getDao(entityClass).loadAll();
    }

    /**
     * 异步查询
     * @param entityClass
     * @param callBack
     */
    public  void queryAllAsync(Class<T> entityClass, QueryListCallBack callBack) {
        mDaoSessing.startAsyncSession().runInTx(() -> {
            List<T> entityList = getDao(entityClass).loadAll();
            callBack.querySuccess(entityList);
        });
    }

    /**
     * 获取entity在数据库中的条数
     * @param entityClass
     * @return
     */
    public long getCount(Class entityClass) {
        return getDao(entityClass).count();
    }

}
