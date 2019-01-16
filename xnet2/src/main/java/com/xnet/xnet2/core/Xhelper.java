package com.xnet.xnet2.core;

import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tcl.token.ndk.ServerEncrypt;
import com.xnet.xnet2.bean.Fidbean;
import com.xnet.xnet2.listener.XBaseListener;
import com.xnet.xnet2.listener.XNormalListener;
import com.xnet.xnet2.listener.XTransferListener;
import com.xnet.xnet2.log.Lgg;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;
import org.xutils.x;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/*
 * Created by qianli.ma on 2019/1/7 0007.
 */
public class Xhelper<T> {

    /**
     * 基础地址 (首次需要设置)
     */
    public static String BASE_URL = "https://www.alcatel-move.com";

    /**
     * API KEY (首次需要设置)
     */
    public static String KEY = "";

    /**
     * TOKEN (首次需要设置)
     */
    public static String ACCESS_TOKEN = "";

    /**
     * 1个用户 = 1个UID (首次需要设置)
     */
    public static String UID = "";

    /**
     * 语言 (首次需要设置)
     */
    public static String LANGUAGE = "";

    /**
     * 超时时间 (30s)
     */
    private static final int TIMEOUT = 30000;

    /**
     * 是否打印框架日志 (按需求设置, T:打印)
     */
    public static boolean PRINT_TAG = false;

    /**
     * 是否打印头部信息 (T: 打印)
     */
    public static boolean PRINT_HEAD = false;

    /**
     * 是否打印传输进度 (T: 打印)
     */
    public static boolean PRINT_PROGRESS = false;

    /**
     * 日志标记
     */
    private static final String TAG = "xhelper";

    // 请求方式
    private static final int GET = 0;
    private static final int POST = 1;
    private static final int PUT = 2;
    private static final int PATCH = 3;
    private static final int HEAD = 4;
    private static final int DELETE = 7;
    private static final int OPTIONS = 8;
    private static final int TRACE = 9;
    private static final int CONNECT = 10;

    /**
     * 可变参数(请求提交所需)
     */
    @Nullable
    private Object object;

    /**
     * 接口地址(请求提交所需)
     */
    private String secUrl;

    /**
     * 日志对象
     */
    private Lgg lgg;

    /* -------------------------------------------- public -------------------------------------------- */

    public Xhelper() {
        if (PRINT_TAG) {
            lgg = Lgg.t(TAG).openClose(true);
        }
    }

    public Xhelper<T> xUrl(String secUrl) {
        this.secUrl = secUrl;
        return this;
    }

    public Xhelper<T> xParam(@Nullable Object object) {
        this.object = object;
        return this;
    }

    /**
     * 请求(get 方式)
     *
     * @param resp 响应
     */
    public void xGet(XNormalListener<T> resp) {
        request(GET, resp);
    }

    /**
     * 请求(post 方式)
     *
     * @param resp 响应
     */
    public void xPost(XNormalListener<T> resp) {
        request(POST, resp);
    }

    /**
     * 请求(put 方式)
     *
     * @param resp 响应
     */
    public void xPut(XNormalListener<T> resp) {
        request(PUT, resp);
    }

    /**
     * 请求(delete 方式)
     *
     * @param resp 响应
     */
    public void xDelete(XNormalListener<T> resp) {
        request(DELETE, resp);
    }

    /**
     * 请求(patch 方式)
     *
     * @param resp 响应
     */
    public void xPatch(XNormalListener<T> resp) {
        request(PATCH, resp);
    }

    /**
     * 请求(head 方式)
     *
     * @param resp 响应
     */
    public void xHead(XNormalListener<T> resp) {
        request(HEAD, resp);
    }

    /**
     * 请求(options 方式)
     *
     * @param resp 响应
     */
    public void xOptions(XNormalListener<T> resp) {
        request(OPTIONS, resp);
    }

    /**
     * 请求(trace 方式)
     *
     * @param resp 响应
     */
    public void xTrace(XNormalListener<T> resp) {
        request(TRACE, resp);
    }

    /**
     * 请求(connect 方式)
     *
     * @param resp 响应
     */
    public void xConnect(XNormalListener<T> resp) {
        request(CONNECT, resp);
    }

    /* -------------------------------------------- private -------------------------------------------- */

    /**
     * 普通请求
     *
     * @param type     请求方式
     * @param listener 响应回调
     */
    private void request(int type, final XNormalListener<T> listener) {
        printNormal("prepare to request params");
        printHead();
        // 1.请求参数
        RequestParams params = getNormalRequestParams();
        // 2.区分类型(默认POST)
        HttpMethod httpMethod = HttpMethod.POST;
        httpMethod = getHttpMethod(type, httpMethod);
        // 3.发起请求
        printNormal("The Http method is " + printHttpType(type) + "; Start to request url is : [" + BASE_URL + secUrl + "]");
        x.http().request(httpMethod, params, new Callback.CommonCallback<String>() {

            @Override
            public void responseBody(UriRequest uriRequest) {

            }

            @Override
            public void onSuccess(String result) {
                printResponseSuccess(result);
                /*
                 * 如果请求的结果没有具体的实体参数, 则服务器返回 {"error_id": 0, "error_msg":"ok"}
                 * 如果请求的结果带有具体的实体参数, 则服务器返回对应的实参json {"aaa": 0, "bbb": "demo"}
                 * 因此只有服务器返回具体实体参数时, 才需要进行json转换
                 * */
                if (!result.contains("error_id")) {
                    T t = toBean(result, listener);
                    listener.successByValue(t);
                    printNormal("[Request] & [json reback] & [turn bean] Successful");
                } else {
                    listener.successNoValue();
                    printNormal("Request Successful but not value reback");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                handleError(ex, listener);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                listener.cancel(cex);
                printCancel(cex);
            }

            @Override
            public void onFinished() {
                listener.finish();
                printFinish();
            }
        });
    }

    /**
     * 切换请求方式类型
     *
     * @param type       类型代号
     * @param httpMethod 请求方式
     * @return 请求方式对象
     */
    private HttpMethod getHttpMethod(int type, HttpMethod httpMethod) {
        switch (type) {
            case GET:
                httpMethod = HttpMethod.GET;
                break;
            case POST:
                httpMethod = HttpMethod.POST;
                break;
            case PUT:
                httpMethod = HttpMethod.PUT;
                break;
            case DELETE:
                httpMethod = HttpMethod.DELETE;
                break;
            case PATCH:
                httpMethod = HttpMethod.PATCH;
                break;
            case HEAD:
                httpMethod = HttpMethod.HEAD;
                break;
            case OPTIONS:
                httpMethod = HttpMethod.OPTIONS;
                break;
            case TRACE:
                httpMethod = HttpMethod.TRACE;
                break;
            case CONNECT:
                httpMethod = HttpMethod.CONNECT;
                break;
        }
        return httpMethod;
    }

    /**
     * 上传图片
     *
     * @param file     图片文件
     * @param listener 回调监听
     */
    public void uploadImage(File file, XTransferListener listener) {
        printNormal("prepare to request params");
        printHead();
        RequestParams imageParams = getImageParams(file);// 准备参数
        printNormal("The Http method is " + printHttpType(POST) + "; Start to request url is : [" + BASE_URL + "/v1.0/fs?type=image&duration=0" + "]");
        x.http().post(imageParams, new Callback.ProgressCallback<String>() {// 开始请求

            @Override
            public void responseBody(UriRequest uriRequest) {

            }

            @Override
            public void onWaiting() {
                listener.waiting();
                printNormal("--> wait to upload");
            }

            @Override
            public void onStarted() {
                listener.start();
                printNormal("--> start to upload");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                listener.loading(total, current, isDownloading);
                if (PRINT_PROGRESS) {
                    printNormal(" progress--> total: " + total + ";current: " + current + ";isDownloading: " + isDownloading);
                }
            }

            @Override
            public void onSuccess(String result) {
                if (result.contains("fid")) {
                    String fid = JSON.parseObject(result, Fidbean.class).getFid();
                    listener.success(fid);
                    printNormal("upload successfule, the [Fid] is : " + fid);
                } else {
                    printNormal("upload successfule, the [Fid] is : Null");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                handleError(ex, listener);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                listener.cancel(cex);
                printCancel(cex);
            }

            @Override
            public void onFinished() {
                listener.finish();
                printFinish();
            }
        });
    }

    /**
     * 下载文件
     *
     * @param fid      需要下载的文件的ID
     * @param path     下载文件需要保存的本地路径
     * @param listener 下载监听
     */
    public void downFile(String fid, String path, XTransferListener listener) {
        printNormal("prepare to request params");
        printHead();
        RequestParams downParam = getDownParam(fid, path);// 准备参数
        printNormal("The Http method is " + printHttpType(GET) + "; Start to request url is : [" + BASE_URL + "/v1.0/fs/" + fid + "]");
        x.http().get(downParam, new Callback.ProgressCallback<File>() {

            @Override
            public void responseBody(UriRequest uriRequest) {

            }

            @Override
            public void onWaiting() {
                listener.waiting();
                printNormal("--> wait to download");
            }

            @Override
            public void onStarted() {
                listener.start();
                printNormal("--> start to download");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                listener.loading(total, current, isDownloading);
                if (PRINT_PROGRESS) {
                    printNormal(" progress--> total: " + total + ";current: " + current + ";isDownloading: " + isDownloading);
                }
            }

            @Override
            public void onSuccess(File file) {
                listener.success(file);
                printNormal("download success, the [file path] is : " + file.getAbsolutePath());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                handleError(ex, listener);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                listener.cancel(cex);
                printCancel(cex);
            }

            @Override
            public void onFinished() {
                listener.finish();
                printFinish();
            }
        });
    }

    /**
     * 普通请求参数
     *
     * @return 请求参数
     */
    private RequestParams getNormalRequestParams() {
        RequestParams requestParams = new RequestParams(BASE_URL + secUrl);
        // 基本信息
        requestParams.setConnectTimeout(TIMEOUT);
        // 头部信息
        requestParams.addHeader("Authorization", getAuthorization());
        requestParams.addHeader("Content-Type", "application/json");
        requestParams.addHeader("Accept-Language", LANGUAGE);
        requestParams.addHeader("User-Agent", android.os.Build.MANUFACTURER + "-" + android.os.Build.MODEL);
        // 参数
        if (object != null) {
            requestParams.setAsJsonContent(true);
            String contentJson = JSON.toJSONString(object);
            requestParams.setBodyContent(contentJson);
            if (PRINT_HEAD) {
                printRequestJson(contentJson);
            }

        }
        return requestParams;
    }

    /**
     * 获取上传图片的参数
     *
     * @param file 待上传文件
     * @return 参数对象
     */
    private RequestParams getImageParams(File file) {
        // 准备参数
        RequestParams requestParams = new RequestParams(BASE_URL + "/v1.0/fs?type=image&duration=0");
        // 基本信息
        requestParams.setConnectTimeout(TIMEOUT);
        // 头部信息
        requestParams.addHeader("Authorization", getAuthorization());
        requestParams.addHeader("Accept-Language", LANGUAGE);
        requestParams.addHeader("User-Agent", android.os.Build.MANUFACTURER + "-" + android.os.Build.MODEL);
        // 参数
        requestParams.addBodyParameter("file", file);
        requestParams.setMultipart(true);/* 这一句必须要加才能上传成功 */
        return requestParams;
    }

    /**
     * 下载参数
     *
     * @param fid      文件ID
     * @param savePath 下载保存的路径
     * @return 参数对象
     */
    private RequestParams getDownParam(String fid, String savePath) {
        // 准备参数
        RequestParams requestParams = new RequestParams(BASE_URL + "/v1.0/fs/" + fid);
        // 基本信息
        requestParams.setConnectTimeout(TIMEOUT);
        // 头部信息
        requestParams.addHeader("Authorization", getAuthorization());
        requestParams.addHeader("Accept-Language", LANGUAGE);
        requestParams.addHeader("User-Agent", android.os.Build.MANUFACTURER + "-" + android.os.Build.MODEL);
        // 保存路径
        requestParams.setSaveFilePath(savePath);
        return requestParams;
    }

    /**
     * 获取加密的认证信息
     */
    private String getAuthorization() {
        ServerEncrypt encrypt = new ServerEncrypt(UID);
        String key = "key=" + KEY + ";";
        String token = "token=" + ACCESS_TOKEN + ";";
        String sign = "sign=" + encrypt.getSign() + ";";
        String timeStamp = "timestamp=" + encrypt.getTimestamp() + ";";
        String newToken = "newtoken=" + encrypt.getNewtoken() + ";";
        return key + token + sign + timeStamp + newToken;
    }

    /**
     * 转换数据为bean
     *
     * @param result 返回字符
     */
    private T toBean(String result, XNormalListener listenenr) {
        /* **** 注意这里一定要使用监听器去获取实现类的泛型 **** */
        Type[] genericInterfaces = listenenr.getClass().getGenericInterfaces();
        Type[] params = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
        return JSONObject.parseObject(result, params[0]);
    }

    private void handleError(Throwable ex, XBaseListener listener) {
        // TODO: 2019/1/11 0011  
        if (ex instanceof HttpException) {// 此处要过滤, 非服务器返回的message强转成HttpException会出错
            String errJson = ((HttpException) ex).getResult();
            if (errJson.contains("error_id")) {
                ServerError error = JSON.parseObject(errJson, ServerError.class);
                listener.serverError(error);
                printServerError(error.getError_id(), error.getError_field(), error.getError_msg());
            } else {
                listener.appError(ex);
                printAppError(ex);
            }
        } else {
            listener.appError(ex);
            printAppError(ex);
        }
    }

    /* -------------------------------------------- log -------------------------------------------- */

    /**
     * 打印头部请求参数
     */
    private void printHead() {
        if (PRINT_HEAD) {
            lgg.ii("Request body: \n"// 请求头信息
                           + "{\n\turl: " + BASE_URL + secUrl // url
                           + "\n\tkey: " + KEY // key
                           + "\n\taccess_token: " // access token
                           + "\n\tuid: " + UID // uid
                           + "\n\tlanguage: " + LANGUAGE // language
                           + "\n\ttimeout: " + TIMEOUT // timeout
            );
        }
    }

    /**
     * 普通打印
     *
     * @param content 备注
     */
    private void printNormal(String content) {
        lgg.ii("-------- " + content + " --------");
    }

    /**
     * 服务器返回成功(未进行转换)
     *
     * @param json 原文
     */
    private void printResponseSuccess(String json) {
        lgg.ii("--> url: [" + secUrl + "] request Successful");
        json = json.replace("{", "response json\n{\n").replace(",", ",\n").replace("}", "\n}");
        lgg.ii(json);
    }

    /**
     * 打印请求json
     *
     * @param json 原文
     */
    private void printRequestJson(String json) {
        json = json.replace("{", "request json\n{\n").replace(",", ",\n").replace("}", "\n}");
        lgg.ii(json);
    }

    /**
     * 服务器返回失败
     *
     * @param err_id    error_id
     * @param err_field error_field
     * @param err_msg   error_msg
     */
    private void printServerError(int err_id, String err_field, String err_msg) {
        lgg.ee("<-- url: [" + secUrl + "] request server Error");
        lgg.ee("<-- { error_id = " + err_id + "; error_field = " + err_field + "; error_msg = " + err_msg + ";");
    }

    /**
     * APP请求出错
     *
     * @param ex 错误对象
     */
    private void printAppError(Throwable ex) {
        lgg.ee("<-- url: [" + secUrl + "] request Error");
        lgg.ee("<-- { error_message = " + ex.getMessage() + " }");
    }

    /**
     * APP请求取消
     *
     * @param cex 错误对象
     */
    private void printCancel(Callback.CancelledException cex) {
        lgg.ww("<-- url: [" + secUrl + "] request app Cancel");
        lgg.ww("<-- { cancel_message = " + cex.getMessage() + " }");
    }

    /**
     * 请求结束
     */
    private void printFinish() {
        lgg.vv("<-- url: [" + secUrl + "] request Finish");
    }

    /**
     * 请求类型描述
     *
     * @param type 类型
     * @return 描述
     */
    private String printHttpType(int type) {
        String httpName = "UNKNOWN";
        httpName = type == 0 ? "GET" : httpName;
        httpName = type == 1 ? "POST" : httpName;
        httpName = type == 2 ? "PUT" : httpName;
        httpName = type == 3 ? "PATCH" : httpName;
        httpName = type == 4 ? "HEAD" : httpName;
        httpName = type == 7 ? "DELETE" : httpName;
        httpName = type == 8 ? "OPTIONS" : httpName;
        httpName = type == 9 ? "TRACE" : httpName;
        httpName = type == 10 ? "CONNECT" : httpName;
        return httpName;
    }
}
