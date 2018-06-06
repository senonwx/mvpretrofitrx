package com.senon.mvpretrofitrx.mvp.activity;

import android.content.Intent;
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
import com.socks.library.KLog;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.main_msg_tv)
    TextView main_msg_tv;
    @BindView(R.id.main_check_btn)
    Button main_check_btn;
    @BindView(R.id.main_check2_btn)
    Button main_check2_btn;
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
    public void logoutResult(BaseResponse<List<Login>> data) {
        ////todo 第二个请求结果。。。
        main_msg_tv.setText(data.getData().toString());
    }

    @Override
    public void setMsg(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
//        return this.bindUntilEvent(ActivityEvent.PAUSE);//绑定到Activity的pause生命周期（在pause销毁请求）
        return this.bindToLifecycle();//绑定activity，与activity生命周期一样
    }


    @OnClick({R.id.main_msg_tv, R.id.main_check_btn,R.id.main_check2_btn,R.id.main_intent_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_msg_tv:
                break;
            case R.id.main_check_btn:
                main_msg_tv.setText("");
                HashMap<String,String> map = new HashMap<>();
                map.put("type","yuantong");
                map.put("postid","11111111111");
//                map.put("mobile","18328008870");
//                map.put("secret","34ba01d602c88790bbe81a7aca8d3a9f");
//                KLog.e("mobile:  "+"18328008870"+"  secret:   "+"34ba01d602c88790bbe81a7aca8d3a9f");
                getPresenter().login(map,true,true);
                break;
            case R.id.main_check2_btn:
                main_msg_tv.setText("");
                HashMap<String,String> map2 = new HashMap<>();
                map2.put("type","yuantong");
                map2.put("postid","11111111111");
                getPresenter().logout(map2,false,true);
                break;
            case R.id.main_intent_btn:
                startActivity(new Intent(LoginActivity.this,NoPresenterActivity.class));
                break;
        }
    }
}
