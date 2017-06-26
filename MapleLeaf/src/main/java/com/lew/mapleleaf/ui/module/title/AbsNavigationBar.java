package com.lew.mapleleaf.ui.module.title;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Richie on 2017/6/22
 */

public abstract class AbsNavigationBar<P extends AbsNavigationBar.Builder.NavigationParams> implements INavigation {
    private P params;
    private View view;

    public AbsNavigationBar(P params) {
        this.params = params;
        createAndBind();
    }

    protected String getString(int id) {
        return this.params.context.getResources().getString(id);
    }

    protected int getColor(int id) {
        return ContextCompat.getColor(this.params.context, id);
    }

    public P getParams() {
        return params;
    }

    protected void setText(int viewID, CharSequence text) {
        TextView view = findViewByID(viewID);
        view.setText(text);
    }

    protected void setOnclickListener(int viewID, View.OnClickListener listener) {
        View view = findViewByID(viewID);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    protected void setImageResource(int viewID, int resourceID) {
        ImageView view = findViewByID(viewID);
        if (view != null) {
            view.setImageResource(resourceID);
        }
    }

    private <T extends View> T findViewByID(int viewID) {
        return (T) view.findViewById(viewID);
    }

    protected void createAndBind() {
        if (params == null) {
            return;
        }
        view = LayoutInflater.from(params.context).inflate(buildLayoutRes(), params.parent, false);
        params.parent.addView(view, 0);
        applyView();
    }


    public abstract static class Builder {
        public abstract AbsNavigationBar create();

        public static class NavigationParams {
            public Context context;
            public ViewGroup parent;

            public NavigationParams(Context context, ViewGroup parent) {
                this.context = context;
                this.parent = parent;
            }
        }
    }
}
