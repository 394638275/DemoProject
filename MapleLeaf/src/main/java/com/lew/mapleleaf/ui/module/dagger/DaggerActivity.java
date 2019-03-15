package com.lew.mapleleaf.ui.module.dagger;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.databinding.ActivityDaggerDemoBinding;
import com.lew.mapleleaf.ui.BaseActivity;

import javax.inject.Inject;

/**
 * Created by Richie 2017/4/11.
 */

public class DaggerActivity extends BaseActivity<DaggerActivityPresenter, ActivityDaggerDemoBinding> implements DaggerActivityContract.View {

    @Inject
    FlowerCollector mFlowerCollector;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dagger_demo;
    }

    @Override
    protected void initView() {
        DaggerDaggerActivityComponent.create().inject(this);

        mViewBinding.tvTextContent.setText(mFlowerCollector.showFlowerName());
    }
}
