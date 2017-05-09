package com.lew.mapleleaf.ui.module.dagger;

import android.widget.TextView;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Richie 2017/4/11.
 */

public class DaggerActivity extends BaseActivity {

    @BindView(R.id.tv_text_content)
    TextView mTextContent;

    @Inject
    FlowerCollector mFlowerCollector;

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleBuilder.buildLeftBackTitle("Dagger Activity");
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_dagger_demo;
    }

    @Override
    protected void initInjector() {
        DaggerDaggerActivityComponent.create().inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {
//        mTextContent.setText(pot.show());
        mTextContent.setText(mFlowerCollector.showFlowerName());
    }
}
