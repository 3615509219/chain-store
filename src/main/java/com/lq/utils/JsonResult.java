package com.lq.utils;

import java.io.Serializable;

public class JsonResult implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int SUCCESS = 1;
    public static final int ERROR = 0;

    /**
     * 返回是否成功的状态, 1表示成功, 0表示失败
     */
    private int state;
    /**
     * 成功时候,返回的JSON数据
     */
    private Object data;
    /**
     * 是错误时候的错误消息
     */
    private String errmsg;
    /**
     * 是错误时候的错误码
     */
    private int errcode;

    public JsonResult() {
        state = SUCCESS;
    }

    public JsonResult(int state, Object data, String errmsg, int errcode) {
        this.state = state;
        this.data = data;
        this.errmsg = errmsg;
        this.errcode = errcode;
    }

    public JsonResult(String errMsg, int errcode) {
        state = ERROR;
        data = null;
        this.errcode = errcode;
        this.errmsg = errMsg;
    }

    public JsonResult(Object data) {
        state = SUCCESS;
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    @Override
    public String toString() {
        return "JsonResult [state=" + state + ", data=" + data + ", errcode=" + errcode + ", errmsg=" + errmsg + "]";
    }

}
