package com.xnet.xnet2.core;

/*
 * Created by qianli.ma on 2019/1/8 0008.
 */
public class ServerError {
    /**
     * 错误ID
     */
    public int error_id;

    /**
     * 错误字段
     */
    public String error_field;

    /**
     * 错误内容
     */
    public String error_msg;

    public ServerError() {
    }

    public ServerError(int error_id, String error_field, String error_msg) {
        this.error_id = error_id;
        this.error_field = error_field;
        this.error_msg = error_msg;
    }

    public int getError_id() {
        return error_id;
    }

    public void setError_id(int error_id) {
        this.error_id = error_id;
    }

    public String getError_field() {
        return error_field;
    }

    public void setError_field(String error_field) {
        this.error_field = error_field;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ServerError{");
        sb.append("\n").append("\t").append("error_id =").append(error_id);
        sb.append("\n").append("\t").append("error_field ='").append(error_field).append('\'');
        sb.append("\n").append("\t").append("error_msg ='").append(error_msg).append('\'');
        sb.append("\n}");
        return sb.toString();
    }
}
