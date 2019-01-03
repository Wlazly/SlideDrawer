package com.github.greendao.module;

/**
 * Created by wzhiqiang on 2019/1/3.
 */

public class UserDbModel  {

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
