package com.lew.mapleleaf.ui.module.base;

import android.view.View;

import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * Created by long on 2016/8/23.
 * 基础 BaseView 接口
 */
public interface IBaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void showContent();

    /**
     * 显示网络错误
     *
     * @param onRetryListener 点击监听
     */
    void showNetError(View.OnClickListener onRetryListener);

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();

    /**
     * 完成刷新, 新增控制刷新
     */
    void finishRefresh();
}
