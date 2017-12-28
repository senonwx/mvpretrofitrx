package com.senon.mvpretrofitrx.mvp.utils;

import android.app.Application;
import android.content.Context;

/**
 * Application类 初始化各种配置
 */
public class ApplicationUtil extends Application {

    private static Context mContext;//全局上下文对象

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }


}
