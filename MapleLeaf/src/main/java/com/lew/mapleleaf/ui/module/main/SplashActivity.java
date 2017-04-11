package com.lew.mapleleaf.ui.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.BaseActivity;
import com.lew.mapleleaf.utils.rx.RxHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by richie 2016/3/31.
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.tv_count_down_seconds)
    TextView mCountDownSeconds;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_start_up;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @SuppressWarnings("unchecked")
    @Override
    protected void updateViews(boolean isRefresh) {
        RxHelper.countdown(2)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        doSkip();
                    }

                    @Override
                    public void onError(Throwable e) {
                        doSkip();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        mCountDownSeconds.setText(String.format(getString(R.string.skip_in_seconds), integer));
                    }
                });
    }

    private void doSkip() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.tv_count_down_seconds)
    public void OnClick(View view){
        doSkip();
    }
}
