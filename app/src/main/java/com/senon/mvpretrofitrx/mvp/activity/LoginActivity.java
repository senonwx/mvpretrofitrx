package com.senon.mvpretrofitrx.mvp.activity;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.senon.mvpretrofitrx.R;
import com.senon.mvpretrofitrx.mvp.base.BaseActivity;
import com.senon.mvpretrofitrx.mvp.base.BaseResponse;
import com.senon.mvpretrofitrx.mvp.contract.LoginContract;
import com.senon.mvpretrofitrx.mvp.fragment.LoginFragment;
import com.senon.mvpretrofitrx.mvp.presenter.LoginPresenter;
import com.senon.mvpretrofitrx.mvp.entity.Login;
import com.senon.mvpretrofitrx.mvp.utils.ToastUtil;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.main_msg_tv)
    TextView main_msg_tv;
    @BindView(R.id.main_check_btn)
    Button main_check_btn;
    @BindView(R.id.frame_lay)
    FrameLayout frame_lay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }
    @Override
    public LoginContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frame_lay, new LoginFragment()).
                commitAllowingStateLoss();

    }

    @Override
    public void result(BaseResponse<List<Login>> data) {
        main_msg_tv.setText(data.getData().toString());
    }

    @Override
    public void setMsg(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @OnClick({R.id.main_msg_tv, R.id.main_check_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_msg_tv:
                break;
            case R.id.main_check_btn:
                HashMap<String,String> map = new HashMap<>();
                map.put("type","yuantong");
                map.put("postid","11111111111");
                getPresenter().login(map,true,true);
                break;
        }
    }
}
