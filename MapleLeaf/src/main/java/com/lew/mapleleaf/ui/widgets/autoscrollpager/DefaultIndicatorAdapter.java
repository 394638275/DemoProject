package com.lew.mapleleaf.ui.widgets.autoscrollpager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DefaultIndicatorAdapter extends PageControlBase.Adapter {
    private Context mContext;
    private GradientDrawable mSelectedDrawable;
    private GradientDrawable mUnSelectedDrawable;

    public DefaultIndicatorAdapter(Context context) {
        mContext = context;

        mSelectedDrawable = new GradientDrawable();
        mSelectedDrawable.setColor(Color.RED);
        mSelectedDrawable.setCornerRadius(Util.dip2px(context, 8));

        mUnSelectedDrawable = new GradientDrawable();
        mUnSelectedDrawable.setColor(Color.YELLOW);
        mUnSelectedDrawable.setCornerRadius(Util.dip2px(context, 8));
    }

    @Override
    public PageControlBase.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(mContext);
        imageView.setClickable(true);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(Util.dip2px(mContext, 8), Util.dip2px(mContext, 8)));
        return new PageControlBase.ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(PageControlBase.ViewHolder holder, int position, int currentPosition) {
        if (position == currentPosition) {
            ((ImageView) holder.itemView).setImageDrawable(mSelectedDrawable);
        } else {
            ((ImageView) holder.itemView).setImageDrawable(mUnSelectedDrawable);
        }
    }
}
