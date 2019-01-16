package com.xnet.xnet2.listener;

/**
 * 回调接口
 *
 */
public interface XNormalListener<T> extends XBaseListener{
    
    /**
     * 请求成功(服务器返回实体参数)
     *
     * @param result 请求结果
     */
    void successByValue(T result);

    /**
     * 请求成功(服务器无返回实体参数)
     */
    void successNoValue();
}
