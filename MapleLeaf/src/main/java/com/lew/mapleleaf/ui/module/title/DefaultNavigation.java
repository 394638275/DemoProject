package com.lew.mapleleaf.ui.module.title;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.lew.mapleleaf.R;

/**
 * Created by Richie on 2017/6/22
 */

public class DefaultNavigation<D extends AbsNavigationBar.Builder.NavigationParams> extends AbsNavigationBar<DefaultNavigation.Builder.DefaultNavigationParams>{
    public DefaultNavigation(Builder.DefaultNavigationParams params) {
        super(params);
    }

    @Override
    public int buildLayoutRes() {
        return R.layout.view_default_title_layout;
    }

    @Override
    public void applyView() {
        setText(R.id.tv_title_center, getParams().title);
        setText(R.id.tv_title_left, getParams().titleLeft);
        setText(R.id.tv_title_right,getParams().titleRight);
    }

    public static class Builder extends AbsNavigationBar.Builder{
        private DefaultNavigationParams params;

        public Builder(Context context, ViewGroup parent) {
            params = new DefaultNavigationParams(context, parent);
        }

        public Builder setTitle(String title){
            params.title = title;
            return this;
        }

        public Builder setLeft(String leftStr){
            params.titleLeft = leftStr;
            return this;
        }

        public Builder setRight(String rightStr) {
            params.titleRight = rightStr;
            return this;
        }

        public Builder setLeftIcon(int iconRes){
            params.leftIconRes = iconRes;
            return this;
        }

        public Builder setRightIcon(int iconRes){
            params.rightIconRes = iconRes;
            return this;
        }

        public Builder setTitleBackGroundColor(int colorRes){
            params.bgColor = colorRes;
            return this;
        }

        public Builder setLeftOnclickListener(View.OnClickListener listener){
            params.leftOnclickListener = listener;
            return this;
        }

        public Builder setRightOnclickListener(View.OnClickListener listener){
            params.rightOnclickListener = listener;
            return this;
        }

        @Override
        public DefaultNavigation<NavigationParams> create() {
            return  new DefaultNavigation<>(params);
        }

        public static class DefaultNavigationParams extends NavigationParams{
            public String title;
            public int leftIconRes;
            public int rightIconRes;
            public View.OnClickListener leftOnclickListener;
            public View.OnClickListener rightOnclickListener;
            public String titleLeft;
            public String titleRight;
            public int bgColor;

            public DefaultNavigationParams(Context context, ViewGroup parent) {
                super(context, parent);
            }
        }
    }
}
