package com.github.greendao.callback;

import java.util.List;

/**
 * Created by wzhiqiang on 2019/1/3.
 */

public interface QueryListCallBack<T> {

     void querySuccess(List<T> object);

}
