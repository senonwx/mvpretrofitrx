package com.senon.mvpretrofitrx.mvp.base;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：senon on 2017/12/28 11:19
 * 邮箱：a1083911695@163.com
 *
 * 返回的数据结构：
 * {
 "data": [],
 "errorCode": 0,
 "errorMsg": ""
 }
 */

public class BaseResponse<T> {

    //序列化名称  拿到我们想要的msg字段而不是errorMsg字段
    @SerializedName("errorMsg")
    private String msg;
    @SerializedName("errorCode")
    private int code;
    private T data;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

}
