package com.lew.mapleleaf.ui.widgets.autoscrollpager;

import android.support.annotation.LayoutRes;
import android.view.View;

public abstract class IBindView {
    private int count;

    public abstract void onBindView(View itemView, int position);

    public abstract int getCount();

    @LayoutRes
    public abstract int onLayoutId();

    public void setCount(int count) {
        this.count = count;
    }

    public int count() {
        return count;
    }
}
