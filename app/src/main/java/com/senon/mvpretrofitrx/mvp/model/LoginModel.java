package com.senon.mvpretrofitrx.mvp.model;

import android.content.Context;
import com.senon.mvpretrofitrx.mvp.api.Api;
import com.senon.mvpretrofitrx.mvp.base.BaseModel;
import com.senon.mvpretrofitrx.mvp.progress.ObserverResponseListener;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;

/**
 * 作者：senon on 2017/12/27 10:33
 * 邮箱：a1083911695@163.com
 */
public class LoginModel<T> extends BaseModel {

    public void login(Context context, HashMap<String,String> map, boolean isDialog, boolean cancelable,
                      ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){

        //当不需要指定是否由dialog时，可以调用这个方法
//        subscribe(context, Api.getApiService().login(map), observerListener);
        subscribe(context, Api.getApiService().login(map), observerListener,transformer,isDialog,cancelable);
    }

    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作

    public void logout(Context context, HashMap<String,String> map, boolean isDialog, boolean cancelable,
                      ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){

        subscribe(context, Api.getApiService().logout(map), observerListener,transformer,isDialog,cancelable);
    }

    public void getChapters(Context context, boolean isDialog, boolean cancelable,
                      ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){

        subscribe(context, Api.getApiService().getChapters(), observerListener,transformer,isDialog,cancelable);
    }

    /**
     * 模拟网络延迟   延迟 3 秒请求
     * @param context
     * @param isDialog
     * @param cancelable
     * @param transformer
     * @param observerListener
     */
    public void getDelayChapters(Context context, boolean isDialog, boolean cancelable,
                                 ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){

        subscribe(context, Api.getApiService().getChapters().delay(3, TimeUnit.SECONDS), observerListener,transformer,isDialog,cancelable);
    }

    /**
     * 合并请求结果
     * @param context
     * @param isDialog
     * @param cancelable
     * @param transformer
     * @param observerListener
     */
    public void zipRequest(Context context,boolean isDialog, boolean cancelable,
                           ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getChapters(),Api.getApiService().getBanner(),
                observerListener,transformer,isDialog,cancelable);
    }

}
