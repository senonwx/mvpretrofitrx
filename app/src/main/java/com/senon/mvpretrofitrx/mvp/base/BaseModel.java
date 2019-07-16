package com.senon.mvpretrofitrx.mvp.base;

import android.content.Context;

import com.senon.mvpretrofitrx.mvp.entity.Banner;
import com.senon.mvpretrofitrx.mvp.entity.Login;
import com.senon.mvpretrofitrx.mvp.progress.ObserverResponseListener;
import com.senon.mvpretrofitrx.mvp.progress.ProgressObserver;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

/**
 * M层父类
 */

public class BaseModel<T> {
    /**
     * 封装线程管理和订阅的过程
     * flag  是否添加progressdialog
     */
    public void subscribe(Context context, final Observable observable, ObserverResponseListener<T> listener,
                          ObservableTransformer<T, T> transformer, boolean isDialog, boolean cancelable) {
        final Observer<T> observer = new ProgressObserver(context, listener, isDialog, cancelable);
        observable.compose(transformer)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void subscribe(Context context, final Observable observable, ObserverResponseListener<T> listener,
                          ObservableTransformer<T, T> transformer) {
        subscribe(context, observable, listener, transformer, true, true);
    }


    /**
     * 合并请求
     *
     * @param context
     * @param observable  请求1
     * @param observable2 请求2
     * @param listener
     * @param transformer
     * @param isDialog
     * @param cancelable
     */
    public void subscribe(Context context, final Observable observable,
                          final Observable observable2, ObserverResponseListener listener,
                          ObservableTransformer transformer, boolean isDialog, boolean cancelable) {
        final Observer observer = new ProgressObserver(context, listener, isDialog, cancelable);
        Observable.zip(observable, observable2, new BiFunction<BaseResponse<List<Login>>, BaseResponse<List<Banner>>, BaseResponse>() {
            @Override
            public BaseResponse<List<Banner>> apply(BaseResponse<List<Login>> listBaseResponse, BaseResponse<List<Banner>> listBaseResponse2) throws Exception {
                if(listBaseResponse != null && listBaseResponse2 != null){
                    //需要做什么 合并嘛！
                    //这里两个请求都返回了  所以我这儿就随便返回其中一个了 当然你也可以返回任意组装的实体
                    return listBaseResponse2;
                }
                return new BaseResponse();
            }
        })
                .compose(transformer)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
