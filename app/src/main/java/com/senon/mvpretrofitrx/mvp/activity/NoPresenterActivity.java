package com.senon.mvpretrofitrx.mvp.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.senon.mvpretrofitrx.R;
import com.senon.mvpretrofitrx.mvp.base.BaseActivity;
import com.senon.mvpretrofitrx.mvp.base.BasePresenter;
import com.senon.mvpretrofitrx.mvp.base.BaseResponse;
import com.senon.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.mvp.entity.Login;
import com.senon.mvpretrofitrx.mvp.model.LoginModel;
import com.senon.mvpretrofitrx.mvp.progress.ObserverResponseListener;
import com.senon.mvpretrofitrx.mvp.utils.ExceptionHandle;
import com.senon.mvpretrofitrx.mvp.utils.ToastUtil;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class NoPresenterActivity extends BaseActivity {

    @BindView(R.id.check_msg_tv)
    TextView check_msg_tv;
    @BindView(R.id.check_btn)
    Button check_btn;

    private LoginModel model;

    @Override
    public int getLayoutId() {
        return R.layout.activity_no_presenter;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        model = new LoginModel();
    }


    @OnClick({R.id.check_msg_tv, R.id.check_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_msg_tv:
                break;
            case R.id.check_btn:
                check_msg_tv.setText("");
                HashMap<String, String> map = new HashMap<>();
                map.put("type", "yuantong");
                map.put("postid", "11111111111");
                getData(map, true, false);
                break;
        }
    }

    private void getData(HashMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.login(this, map, isDialog, cancelable,
                this.bindToLifecycle(), new ObserverResponseListener() {
                    @Override
                    public void onNext(Object o) {
                        //这一步是必须的，判断view是否已经被销毁
                        if (this != null) {
                            //设置数据。。。
                            BaseResponse<List<Login>> data = (BaseResponse<List<Login>>) o;
                            setData(data);
                        }
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        if (this != null) {
                            ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                        }
                    }
                });
    }

    private void setData(BaseResponse<List<Login>> data) {
        check_msg_tv.setText(data.getData().toString());
    }
}
