package com.lew.mapleleaf.ui.fragments;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.base.BaseFragment;
import com.lew.mapleleaf.ui.widgets.progresslayout.ProgressView;
import com.lew.mapleleaf.ui.widgets.progresslayout.ProgressListener;
import com.lew.mapleleaf.utils.logger.Logger;

/**
 * Created by asus 2016/3/26.
 */
public class SecondFragment extends BaseFragment {
    ProgressView progressView;
    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initView() {
        progressView = findView(R.id.progressLayout);
        progressView.setProgressListener(new ProgressListener() {
            @Override
            public void onProgressCompleted() {
                Logger.i(TAG, "onProgressCompleted");
            }

            @Override
            public void onProgressChanged(int seconds) {
                Logger.i(TAG, "onProgressChanged: " + seconds);
            }
        });
    }

    @Override
    protected void lazyLoadData() {
        progressView.start();
        Logger.d("lazyLoadData");
    }

    @Override
    protected void initData() {
        Logger.d("initData");
    }
}
