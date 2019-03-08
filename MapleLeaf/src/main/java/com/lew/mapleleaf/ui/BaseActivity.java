package com.lew.mapleleaf.ui;

import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.lew.mapleleaf.base.BaseView;
import com.lew.mapleleaf.base.DataBindingActivity;
import com.lew.mapleleaf.base.BasePresenter;
import com.lew.mapleleaf.utils.InstanceUtil;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Richie 2017/4/7.
 */

public abstract class BaseActivity<T extends BasePresenter, B extends ViewDataBinding> extends DataBindingActivity<B> {

    protected String TAG = getClass().getSimpleName();

    protected T mPresenter;

    @Override
    protected void initPresenter() {
        if (this instanceof BaseView && this.getClass().getGenericSuperclass() instanceof ParameterizedType && ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments().length > 0) {
            Class mPresenterClass = (Class) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
            mPresenter = InstanceUtil.getInstance(mPresenterClass);
            if (mPresenter != null) {
                mPresenter.setView(this);
            }
        } else {
            Log.e(TAG, "initPresenter error");
        }
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
