package com.xnet.xnet2.listener;

import java.io.File;

/*
 * Created by qianli.ma on 2019/1/9 0009.
 */
public interface XTransferListener extends XBaseListener{

    /**
     * 传输等待中
     */
    void waiting();

    /**
     * 传输开始
     */
    void start();

    /**
     * 传输进度
     *
     * @param total         总大小
     * @param current       当前进度
     * @param isDownloading 是否正在传输
     */
    void loading(long total, long current, boolean isDownloading);

    /**
     * 传输成功(回调FID, 一般用于上传成功时的回调)
     *
     * @param fid 成功后服务器返回的fid
     */
    void success(String fid);

    /**
     * 传输成功(回调FILE, 一般用于下载成功时的回调)
     *
     * @param file 成功后服务器返回File
     */
    void success(File file);
}
