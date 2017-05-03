package com.lew.mapleleaf.ui;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lew.mapleleaf.ui.module.base.IBasePresenter;
import com.lew.mapleleaf.ui.module.base.IBaseView;
import com.lew.mapleleaf.ui.module.main.TitleBuilder;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;

public abstract class BaseFragment<T extends IBasePresenter> extends RxFragment implements IBaseView {

    protected String TAG = getClass().getSimpleName();
    protected T mPresenter;
    private boolean mIsMulti = false;
    protected View mRootView;
    protected TitleBuilder mTitleBuilder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), container, false);
            ButterKnife.bind(this, mRootView);
            initInjector();
            initViews(mRootView);
            initTitle(mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @CallSuper
    private void initTitle(View mRootView) {
        mTitleBuilder = new TitleBuilder(mRootView);
        mTitleBuilder.buildLeftBackTitle("这是标题", null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mRootView) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && !mIsMulti && mRootView != null) {
            mIsMulti = true;
            lazyLoadData(false);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && !mIsMulti && mRootView != null) {
            mIsMulti = true;
            lazyLoadData(false);
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showNetError(View.OnClickListener onRetryListener) {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public void finishRefresh() {

    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    protected abstract int attachLayoutRes();

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews(View rootView);

    /**
     * 更新视图控件
     *
     * @param isRefresh 新增参数，用来判断是否为下拉刷新调用，下拉刷新的时候不应该再显示加载界面和异常界面
     */
    protected abstract void lazyLoadData(boolean isRefresh);
}