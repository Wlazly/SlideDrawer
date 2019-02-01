package com.xnet.xnet2.listener;

import com.xnet.xnet2.core.ServerError;

public abstract class XNormalCallback<T> implements XNormalListener<T> {

    public void kickOff(ServerError serverError) {
//        KickOffBean kickBean = new KickOffBean();
//        kickBean.setKickOffTime(System.currentTimeMillis());
//        kickBean.setError(serverError);
//        EventBus.getDefault().postSticky(kickBean);
    }

}
