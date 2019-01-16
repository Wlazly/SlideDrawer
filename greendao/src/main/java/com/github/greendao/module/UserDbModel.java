package com.github.greendao.module;

import com.alibaba.fastjson.JSONObject;
import com.github.greendao.bean.user.LoginBean;
import com.github.greendao.bean.user.UserBean;
import com.github.greendao.bean.user.UserDbBean;
import com.github.greendao.bean.user.UserDbBeanDao;
import com.github.greendao.core.BaseDbModel;

import org.greenrobot.greendao.query.WhereCondition;

/**
 * Created by wzhiqiang on 2019/1/3.
 */

public class UserDbModel extends BaseDbModel {


    /**
     * 获取登陆信息
     * @return
     */
    public static LoginBean getLoginBean() {
        UserDbBean userDbEntity = getUserDbBean();
        if (userDbEntity != null){
            LoginBean loginInfoEntity = JSONObject.parseObject(userDbEntity.getLoginInfo(), LoginBean.class);
            return loginInfoEntity;
        }
        return null;
    }

    /**
     * 设置登陆信息,登陆需要或者刷新token
     * @param bean
     */
    public static void setLoginBean(LoginBean bean) {
        if (bean == null){
            UserDbBean userDbEntity = getUserDbBean();
            delete(userDbEntity);
        }else {
            UserDbBean userDbEntity = getUserDbBean();
            if (userDbEntity != null){
                LoginBean loginBean = JSONObject.parseObject(userDbEntity.getLoginInfo(),LoginBean.class);
                loginBean.copy(bean);
                userDbEntity.setLoginInfo(JSONObject.toJSONString(loginBean));
                update(userDbEntity);
            }else {
                userDbEntity = new UserDbBean();
                userDbEntity.setLoginInfo(JSONObject.toJSONString(bean));
                userDbEntity.setActive(true);
                userDbEntity.setUserId(bean.getUid());
                insert(userDbEntity);
            }
        }

    }

    /**
     * 获取个人基本信息
     */
    public static UserBean getUserBean(){
        UserDbBean userDbEntity = getUserDbBean();
        if (userDbEntity != null){
            UserBean userBean = JSONObject.parseObject(userDbEntity.getUserInfo(), UserBean.class);
            return userBean;
        }
        return null;
    }

    /**
     * 设置个人基本信息
     * @param userBean
     */
    public static void setUserBean(UserBean userBean) {
        UserDbBean userDbBean = getUserDbBean();
        if (userDbBean != null){
            userDbBean.setUserInfo(JSONObject.toJSONString(userBean));
            update(userDbBean);
        }else {
            userDbBean = new UserDbBean();
            userDbBean.setUserInfo(JSONObject.toJSONString(userBean));
            userDbBean.setActive(true);
            userDbBean.setUserId(userBean.getUid());
            insert(userDbBean);
        }
    }

    /**
     * 获取uid
     * @return
     */
    public static String getUid() {
        return getUserDbBean()!= null?getUserDbBean().getUserId():null;
    }

    /**
     * 获取个人数据库bean
     * @return
     */
    private static UserDbBean getUserDbBean() {
        UserDbBean userDbEntity = querySingle(UserDbBean.class,new WhereCondition[]{UserDbBeanDao.Properties.Active.eq(true)});
        return userDbEntity;
    }
}
