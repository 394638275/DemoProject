package com.lew.mapleleaf.ui.widgets.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lew.mapleleaf.R;

public class ColumnHorizontalScrollView extends HorizontalScrollView {
    protected View mRootView;
    protected LinearLayout mColumnContainer;
    protected ImageButton mAddMore;
    protected RelativeLayout mColumnRoot;
    protected View mLeftShadow;
    protected View mRightShadow;
    protected Context mContext;
    protected int mWidth;

    public ColumnHorizontalScrollView(Context context) {
        this(context, null);
    }

    public ColumnHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColumnHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.view_column_horizontal_scrollview, this);
        mColumnContainer = (LinearLayout) mRootView.findViewById(R.id.ll_main_page_title_content);
        mAddMore = (ImageButton) mRootView.findViewById(R.id.iv_more_columns);
        mLeftShadow = mRootView.findViewById(R.id.shade_left);
        mRightShadow = mRootView.findViewById(R.id.shade_right);
    }

    /**
     * 在拖动的时候执行
     */
    @Override
    protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
        shade_ShowOrHide();
        if (mColumnContainer != null && mLeftShadow != null && mRightShadow != null && mAddMore != null
                && mColumnRoot != null) {
            if (mColumnContainer.getWidth() <= mWidth) {
                mLeftShadow.setVisibility(View.GONE);
                mRightShadow.setVisibility(View.GONE);
            }
        } else {
            return;
        }
        if (paramInt1 == 0) {
            mLeftShadow.setVisibility(View.GONE);
            mRightShadow.setVisibility(View.VISIBLE);
            return;
        }
        if (mColumnContainer.getWidth() - paramInt1 + mAddMore.getWidth() + mColumnRoot.getLeft() == mWidth) {
            mLeftShadow.setVisibility(View.VISIBLE);
            mRightShadow.setVisibility(View.GONE);
            return;
        }
        mLeftShadow.setVisibility(View.VISIBLE);
        mRightShadow.setVisibility(View.VISIBLE);
    }

    /**
     * 判断左右阴影的显示隐藏效果
     */
    public void shade_ShowOrHide() {
        if (mColumnContainer != null) {
            measure(0, 0);
            // 如果整体宽度小于屏幕宽度的话，那左右阴影都隐藏
            if (mWidth >= getMeasuredWidth()) {
                mLeftShadow.setVisibility(View.GONE);
                mRightShadow.setVisibility(View.GONE);
            }
        } else {
            return;
        }
        // 如果滑动在最左边时候，左边阴影隐藏，右边显示
        if (getLeft() == 0) {
            mLeftShadow.setVisibility(View.GONE);
            mRightShadow.setVisibility(View.VISIBLE);
            return;
        }
        // 如果滑动在最右边时候，左边阴影显示，右边隐藏
        if (getRight() == getMeasuredWidth() - mWidth) {
            mLeftShadow.setVisibility(View.VISIBLE);
            mRightShadow.setVisibility(View.GONE);
            return;
        }
        // 否则，说明在中间位置，左、右阴影都显示
        mLeftShadow.setVisibility(View.VISIBLE);
        mRightShadow.setVisibility(View.VISIBLE);
    }
}
