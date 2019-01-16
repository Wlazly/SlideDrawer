package com.github.greendao.module;

import android.text.TextUtils;

import com.github.greendao.bean.device.DeviceBean;
import com.github.greendao.bean.device.DeviceDbBean;
import com.github.greendao.bean.device.DeviceDbBeanDao;
import com.github.greendao.bean.user.UserDbBeanDao;
import com.github.greendao.core.BaseDbModel;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzhiqiang on 2019/1/14.
 */
public class DeviceDbModel extends BaseDbModel{

    /**
     * 获取所有设备id
     * @return
     */
    public static List<String> getAllDeviceId(){
        String userId = UserDbModel.getUid();
        List<String> deviceList = new ArrayList<>();
        List<DeviceDbBean> deviceDbBeanList = queryAll(userId);
        for (DeviceDbBean deviceDbBean : deviceDbBeanList) {
            deviceList.add(deviceDbBean.getDeviceId());
        }
        return deviceList;
    }

    /**
     * 清除所有设备记录，只对退出登陆使用
     */
    public static void clearAllDevice(){
        String userId = UserDbModel.getUid();
        List<DeviceDbBean> deviceDbBeanList = queryAll(userId);
        delete(deviceDbBeanList);
    }


    /**
     * 获取单个设备
     * @param deviceId  设备的deviceId
     * @return
     */
    public static DeviceBean getDeviceBean(String deviceId) {
        String userId = UserDbModel.getUid();
        DeviceDbBean deviceDbBean = querySingle(DeviceDbBean.class,new WhereCondition[]{DeviceDbBeanDao.Properties.DeviceId.eq(deviceId),
                DeviceDbBeanDao.Properties.UserId.eq(userId)});
        DeviceBean deviceBean = new DeviceBean();
        deviceBean.copy(deviceDbBean);
        return deviceBean;
    }

    /**
     * 设置设备
     * @param deviceBean
     */
    public static void setDeviceBean(DeviceBean deviceBean){
        String userId = deviceBean.getUserId();
        String deviceId = deviceBean.getDeviceId();
        if (TextUtils.isEmpty(userId)){
            userId = UserDbModel.getUid();
        }
        DeviceDbBean deviceDbBean = query(userId,deviceId);
        if (deviceDbBean == null){
            deviceDbBean = new DeviceDbBean();
        }
        deviceDbBean = deviceBean.getDbBean(deviceDbBean);
        deviceDbBean.setUserId(userId);
        deviceDbBean.setDeviceId(deviceId);
        insertOrUpdate(deviceDbBean);
    }


    /**
     * 查询单个设备，private 只会自己使用
     * @param userId
     * @param deviceId
     * @return
     */
    private static DeviceDbBean query(String userId,String deviceId) {
        DeviceDbBean deviceDbBean = querySingle(DeviceDbBean.class,new WhereCondition[]{DeviceDbBeanDao.Properties.DeviceId.eq(deviceId),
                DeviceDbBeanDao.Properties.UserId.eq(userId)});
        return deviceDbBean;
    }

    /**
     * 查询所有设备，private 只会自己使用
     * @param userId
     * @return
     */
    private static List<DeviceDbBean> queryAll(String userId) {
        List<DeviceDbBean> deviceDbBeanList = queryList(DeviceDbBean.class,new WhereCondition[]{UserDbBeanDao.Properties.UserId.eq(userId)});
        return deviceDbBeanList;
    }
}
