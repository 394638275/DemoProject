package com.lew.mapleleaf.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.module.base.IBasePresenter;
import com.lew.mapleleaf.ui.module.base.IBaseView;
import com.lew.mapleleaf.ui.module.main.TitleBuilder;
import com.lew.mapleleaf.utils.common.ScreenUtils;
import com.lew.mapleleaf.utils.logger.Logger;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Richie 2017/4/7.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity implements IBaseView {

    protected String TAG = getClass().getSimpleName();

    protected T mPresenter;
    protected Toolbar mToolbar;
    protected TitleBuilder mTitleBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerse();
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        initTitle();
        initInjector();
        initViews();
        initData();
        updateViews(false);
    }

    @CallSuper
    protected void initTitle() {
        mTitleBuilder = new TitleBuilder(this);
        mToolbar = (Toolbar) findViewById(R.id.title_toolbar);
        if (mToolbar != null){
            int statusHeight = ScreenUtils.getStatusHeight(this);
            mToolbar.getLayoutParams().height += statusHeight;
            mToolbar.setPadding(mToolbar.getPaddingLeft(), statusHeight, mToolbar.getPaddingRight(), mToolbar.getPaddingBottom());
        } else {
            Logger.e(TAG, "need a title ? can not find a toolbar with id: toolbar_title");
        }
    }

    private void setImmerse() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    protected abstract void initData();

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews(boolean isRefresh);

    @Override
    public void showLoading() {
//        if (mLoadingLayout != null){
//            mLoadingLayout.showLoading();
//        }
    }

    @Override
    public void showContent() {
//        if (mLoadingLayout != null){
//            mLoadingLayout.showContent();
//        }
    }

    @Override
    public void showNetError(View.OnClickListener onRetryListener) {
//        if (mLoadingLayout != null){
//            mLoadingLayout.showError();
//        }
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public void finishRefresh() {

    }

    /**
     * 添加 Fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

}
