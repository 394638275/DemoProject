package com.lew.mapleleaf.ui.module.main;

import android.content.Intent;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.databinding.ActivityStartUpBinding;
import com.lew.mapleleaf.ui.BaseActivity;
import com.lew.mapleleaf.ui.module.splash.SplashContract;

/**
 * Created by richie 2016/3/31.
 */
public class SplashActivity extends BaseActivity<SplashPresenter, ActivityStartUpBinding> implements SplashContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start_up;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setCountDown(Integer integer) {
        mViewBinding.tvCountDownSeconds.setText(String.format(getResources().getString(R.string.second_count), integer));
    }

    @Override
    public void doSkip() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
