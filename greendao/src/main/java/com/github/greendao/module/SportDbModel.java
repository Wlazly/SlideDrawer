package com.github.greendao.module;

import com.github.greendao.bean.sport.SportBean;
import com.github.greendao.bean.sport.SportBeanDao;
import com.github.greendao.core.BaseDbModel;
import com.github.greendao.core.dbCallback.InsertCallBack;
import com.github.greendao.core.dbCallback.QueryListCallBack;

import org.greenrobot.greendao.query.WhereCondition;
import java.util.List;

/**
 * Created by wzhiqiang on 2019/1/15.
 */

public class SportDbModel extends BaseDbModel {

    /**
     * 插入单条数据
     * @param bean
     */
    public void setBean(SportBean bean){
        String userId = UserDbModel.getUid();
        bean.setUserId(userId);
        insert(bean);
    }

    /**
     * 批量插入数据
     * @param beanList
     * @param callBack
     */
    public void setBeanList(List<SportBean> beanList, InsertCallBack callBack){
        String userId = UserDbModel.getUid();
        for (SportBean sportBean : beanList) {
            sportBean.setUserId(userId);
        }
        insertOrUpdateAsnyc(beanList,() -> {
            if (callBack != null) {
                callBack.insertSuccess();
            }
        });
    }

    /**
     * 获取设备的所有运动数据，默认按开始时间从小到大排返回数据
     * @param deviceId
     * @return
     */
    public static void getBean(String deviceId,QueryListCallBack callBack){
        String userId = UserDbModel.getUid();
        queryListAsync(SportBean.class,new WhereCondition[] {
                SportBeanDao.Properties.DeviceId.eq(deviceId),
                SportBeanDao.Properties.UserId.eq(UserDbModel.getUid()),},
                ASC,
                SportBeanDao.Properties.StartTime,
                list -> {
                    if (callBack != null) {
                        callBack.querySuccess(list);
                    }
                });
    }

    /**
     * 获取设备的某个时间段的数据，默认按开始时间从小到大排返回数据
     * @param deviceId 设备id
     * @param startTime 运动开始时间
     * @param endTime 运动结束时间
     * @param callBack
     */
    public static void getBean(String deviceId, long startTime, long endTime, QueryListCallBack callBack) {
        queryListAsync(SportBean.class, new WhereCondition[]{
            SportBeanDao.Properties.DeviceId.eq(deviceId),
            SportBeanDao.Properties.UserId.eq(UserDbModel.getUid()),
            SportBeanDao.Properties.StartTime.ge(startTime),
            SportBeanDao.Properties.EndTime.ge(endTime)},
            ASC,
            SportBeanDao.Properties.StartTime,
            list -> {
                if (callBack != null) {
                    callBack.querySuccess(list);
                }
            });
    };
}
