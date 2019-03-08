package com.lew.mapleleaf.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by long on 2016/8/23.
 * 基础 Presenter
 */
public abstract class BasePresenter<V> {
    protected V mView;
    protected CompositeDisposable mCompositeSubscription = new CompositeDisposable();

    public void setView(V v) {
        this.mView = v;
        this.onAttach();
    }

    protected abstract void onAttach();

    protected void onDetach() {
        mCompositeSubscription.dispose();
    }
}
