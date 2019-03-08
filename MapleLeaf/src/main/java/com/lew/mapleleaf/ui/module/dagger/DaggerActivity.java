package com.lew.mapleleaf.ui.module.dagger;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.BaseActivity;

import javax.inject.Inject;

/**
 * Created by Richie 2017/4/11.
 */

public class DaggerActivity extends BaseActivity {

    @Inject
    FlowerCollector mFlowerCollector;

//    @Override
//    protected void initTitle() {
//        super.initTitle();
////        mTitleBuilder.buildLeftBackTitle("Dagger Activity");
//
//        DefaultNavigation navigation = new DefaultNavigation.Builder(this, (ViewGroup) findViewById(R.id.title_container))
//                .setTitle("这是标题").setLeft("这是返回").setLeftOnclickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        finish();
//                    }
//                }).create();
//    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_dagger_demo;
    }

//    @Override
//    protected void initInjector() {
//        DaggerDaggerActivityComponent.create().inject(this);
//    }

    @Override
    protected void initData() {

    }
}
