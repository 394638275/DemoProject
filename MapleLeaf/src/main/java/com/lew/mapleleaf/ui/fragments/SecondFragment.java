package com.lew.mapleleaf.ui.fragments;

import android.view.View;
import android.widget.Button;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.BaseFragment;
import com.lew.mapleleaf.ui.widgets.views.TextImageButton;

/**
 * Created by asus 2016/3/26.
 */
public class SecondFragment extends BaseFragment {
//    ProgressView progressView;
    private TextImageButton mButton;
    private Button btn;

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initView() {
//        progressView = findView(R.id.progressLayout);
        mButton = findView(R.id.text_image_button);

        btn = findView(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButton.setText("BBBBBB");
            }
        });
//        progressView.setProgressListener(new ProgressListener() {
//            @Override
//            public void onProgressCompleted() {
//                Logger.simple("onProgressCompleted");
//            }
//
//            @Override
//            public void onProgressChanged(int seconds) {
//                Logger.simple(TAG, seconds + "", true);
//            }
//        });
        mButton.setText("aaaaaaaaa");
    }

    @Override
    protected void lazyLoadData() {
//        progressView.start();
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        progressView.cancel();
    }
}
