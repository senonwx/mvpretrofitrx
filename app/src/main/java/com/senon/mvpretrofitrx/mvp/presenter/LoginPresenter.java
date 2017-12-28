package com.senon.mvpretrofitrx.mvp.presenter;

import android.content.Context;
import com.senon.mvpretrofitrx.mvp.base.BaseResponse;
import com.senon.mvpretrofitrx.mvp.contract.LoginContract;
import com.senon.mvpretrofitrx.mvp.entity.Login;
import com.senon.mvpretrofitrx.mvp.model.LoginModel;
import com.senon.mvpretrofitrx.mvp.progress.ObserverResponseListener;
import com.senon.mvpretrofitrx.mvp.utils.ExceptionHandle;

import java.util.HashMap;
import java.util.List;

/**
 * 作者：senon on 2017/12/27 10:34
 * 邮箱：a1083911695@163.com
 */
public class LoginPresenter extends LoginContract.Presenter {

    private LoginModel model;
    private Context context;

    public LoginPresenter(Context context) {
        this.model = new LoginModel();
        this.context = context;
    }

    @Override
    public void login(HashMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.login(context, map, isDialog, cancelable, new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                getView().result((BaseResponse< List<Login>>) o);
                getView().setMsg("请求成功");
            }
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                //// TODO: 2017/12/28 自定义处理异常
//                ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
            }
        });
    }

}
