package com.lew.mapleleaf.ui.module.main;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.lew.mapleleaf.R;

import java.lang.ref.WeakReference;

/**
 * Created by Richie 2017/4/27.
 */

public class TitleBuilder {
    public static final int EMPTY_RESOURCE = -1;
    private static final int DEFAULT_BACK = -2;
    private WeakReference<Activity> reference;
    private View mMainView;

    public TitleBuilder(Activity activity) {
        reference = new WeakReference<>(activity);
        ViewStub stub = (ViewStub) activity.findViewById(R.id.title_container);
        if (stub != null) {
            stub.setLayoutResource(R.layout.view_title_layout);
            mMainView = stub.inflate();
        }
    }

    public TitleBuilder(View mRootView) {
        reference = new WeakReference<>((Activity) mRootView.getContext());
        ViewStub stub = (ViewStub) mRootView.findViewById(R.id.title_container);
        if (stub != null) {
            stub.setLayoutResource(R.layout.view_title_layout);
            mMainView = stub.inflate();
        }
    }

    public TitleBuilder buildLeftBackTitle(String title, View.OnClickListener onClickListener) {
        final Activity activity = reference.get();
        if (activity != null && mMainView != null) {
            Toolbar toolbar = (Toolbar) mMainView.findViewById(R.id.title_toolbar);
            toolbar.setNavigationIcon(R.drawable.title_back);
            if (onClickListener != null) {
                toolbar.setNavigationOnClickListener(onClickListener);
            } else {
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.finish();
                    }
                });
            }

            TextView titleText = (TextView) mMainView.findViewById(R.id.title_text);
            titleText.setText(title);
        }
        return this;
    }
}
