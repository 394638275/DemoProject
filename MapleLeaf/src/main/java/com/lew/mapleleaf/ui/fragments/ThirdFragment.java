package com.lew.mapleleaf.ui.fragments;

import android.view.View;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.base.BaseFragment;
import com.lew.mapleleaf.ui.widgets.progresslayout.ProgressLayout;
public class ThirdFragment extends BaseFragment implements View.OnClickListener{

    private ProgressLayout progressLayout;

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            progressLayout.showLoading();
        }
    };

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_third;
    }

    @Override
    protected void initView() {
        progressLayout = findView(R.id.progress_container);
    }

    @Override
    protected void lazyLoadData() {
        progressLayout.showLoading();
    }

    @Override
    protected void initData() {
        findView(R.id.loading_btn).setOnClickListener(this);
        findView(R.id.content_btn).setOnClickListener(this);
        findView(R.id.none_btn).setOnClickListener(this);
        findView(R.id.net_error_btn).setOnClickListener(this);
        findView(R.id.failed_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loading_btn:
                progressLayout.showLoading();
                break;

            case R.id.content_btn:
                progressLayout.showContent();
                break;

            case R.id.none_btn:
                progressLayout.showNone(retryListener);
                break;

            case R.id.net_error_btn:
                progressLayout.showNetError(retryListener);
                break;

            case R.id.failed_btn:
                progressLayout.showFailed(retryListener);
                break;

            default:
                break;
        }
    }
}
