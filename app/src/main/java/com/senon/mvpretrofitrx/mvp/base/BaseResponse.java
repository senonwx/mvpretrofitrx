package com.senon.mvpretrofitrx.mvp.base;

/**
 * 作者：senon on 2017/12/28 11:19
 * 邮箱：a1083911695@163.com
 */

public class BaseResponse<T> {

    private String msg;
    private int code;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
