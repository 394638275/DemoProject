package com.lew.mapleleaf.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.widgets.views.SwipeBackLayout;

/**
 * author: LQ
 * date: 2019/3/7 9:58
 * description:
 */
public abstract class DataBindingActivity<B extends ViewDataBinding> extends AppCompatActivity {
    protected Toolbar mToolbar;
    public B mViewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(getLayoutId(), null, false);
        mViewBinding = DataBindingUtil.bind(rootView);
        setContentViewLocal(getLayoutId(), rootView);
        initPresenter();
        initToolbar();
        initData();
    }

    private void setContentViewLocal(int layoutId, View rootView) {
        boolean isNotSwapBack = layoutId == R.layout.activity_main || layoutId == R.layout.activity_start_up;
        setContentView(isNotSwapBack ? rootView : getContainer(rootView));
    }

    private View getContainer(View rootView) {
        rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.alpha_white));
        View container = getLayoutInflater().inflate(R.layout.activity_base, null, false);
        SwipeBackLayout swipeBackLayout = container.findViewById(R.id.swipe_back_layout);
        final View shadow = container.findViewById(R.id.iv_shadow);
        swipeBackLayout.addView(rootView);
        swipeBackLayout.setOnScroll(new SwipeBackLayout.OnScrollListener() {
            @Override
            public void complete(float i) {
                shadow.setAlpha(1 - i);
            }
        });
        return container;
    }

    protected void initToolbar() {
        mToolbar = findViewById(R.id.title_toolbar);
        if (mToolbar == null) {
            return;
        }
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract int getLayoutId();

}
