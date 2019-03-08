package com.lew.mapleleaf.ui.module.main;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.MapleLeafApplication;

import java.lang.ref.WeakReference;

/**
 * Created by Richie 2017/4/27.
 */
public class TitleBuilder {
    private static final int EMPTY_RESOURCE = -1;
    private WeakReference<Activity> reference;
    private WeakReference<View> viewReference;

    public TitleBuilder(Activity activity) {
        reference = new WeakReference<>(activity);
    }

    public TitleBuilder(View mRootView) {
        viewReference = new WeakReference<>(mRootView);
    }

    public TitleBuilder buildTitle(int titleRes){
        return buildTitle(MapleLeafApplication.getInstance().getString(titleRes));
    }

    public TitleBuilder buildTitle(CharSequence titleStr){
        return buildTitle(EMPTY_RESOURCE, titleStr, null);
    }

    public TitleBuilder buildLeftBackTitle(int titleRes){
        return buildLeftBackTitle(MapleLeafApplication.getInstance().getString(titleRes));
    }

    public TitleBuilder buildLeftBackTitle(CharSequence title){
        return buildTitle(R.drawable.title_back, title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reference != null){
                    reference.get().finish();
                } else {
                    ((Activity)viewReference.get().getContext()).finish();
                }
            }
        });
    }

    public TitleBuilder buildTitle(int leftDrawableRes, CharSequence title, View.OnClickListener onClickListener) {
        if (reference != null) {
            final Activity activity = reference.get();
            Toolbar toolbar = (Toolbar) activity.findViewById(R.id.title_toolbar);
            setTitleLeft(leftDrawableRes, onClickListener, toolbar);
            TextView titleText = (TextView) activity.findViewById(R.id.title_text);
            titleText.setText(title);
        } else {
            final View rootView = viewReference.get();
            Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.title_toolbar);
            setTitleLeft(leftDrawableRes, onClickListener, toolbar);
            TextView titleText = (TextView) rootView.findViewById(R.id.title_text);
            titleText.setText(title);
        }
        return this;
    }

    private void setTitleLeft(int leftDrawableRes, View.OnClickListener onClickListener, Toolbar toolbar) {
        if (leftDrawableRes > 0){
            toolbar.setNavigationIcon(leftDrawableRes);
        }
        if (onClickListener != null) {
            toolbar.setNavigationOnClickListener(onClickListener);
        }
    }
}
