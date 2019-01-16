package com.xnet.xnet2.listener;

import com.xnet.xnet2.core.ServerError;

import org.xutils.common.Callback;

/*
 * Created by qianli.ma on 2019/1/11 0011.
 */
public interface XBaseListener {
    
    /**
     * 客户端错误
     */
    void appError(Throwable ex);

    /**
     * 服务器错误
     */
    void serverError(ServerError error);

    /**
     * 请求取消
     */
    void cancel(Callback.CancelledException cex);

    /**
     * 请求完成(无论成功与否)
     */
    void finish();
}
