package com.github.greendao.module;

import com.github.greendao.Entity.User;

/**
 * Created by wzhiqiang on 2019/1/3.
 */

public class UserDbModel extends BaseDbModel<User,Long> {

    private UserDbModel() {
        super();
    }

    private static UserDbModel instance;

    public static UserDbModel getInstance(){
        if (instance == null) {
            synchronized (UserDbModel.class) {
                instance = new UserDbModel();
            }
        }
        return instance;
    }


}
