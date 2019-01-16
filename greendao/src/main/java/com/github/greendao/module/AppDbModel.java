package com.github.greendao.module;

import com.github.greendao.bean.user.AppBean;
import com.github.greendao.core.BaseDbModel;

/**
 * Created by wzhiqiang on 2019/1/11.
 */

public class AppDbModel extends BaseDbModel {

    /**
     * 设置版本信息
     * @param appDbBean
     */
    public static void setBean(AppBean appDbBean){
        insertOrUpdate(appDbBean);
    }

    /**
     * 获取版本信息
     * @return
     */
    public static AppBean getBean(){
        AppBean appBean = querySingle(AppBean.class, null);
        return appBean;
    }
}
