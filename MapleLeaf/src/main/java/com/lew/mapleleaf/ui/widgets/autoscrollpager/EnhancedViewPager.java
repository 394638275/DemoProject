package com.lew.mapleleaf.ui.widgets.autoscrollpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

import com.lew.mapleleaf.utils.logger.Logger;

import java.lang.reflect.Field;

/**
 * 增强的ViewPager，增加更多控制
 */
public class EnhancedViewPager extends ViewPager {
    private ViewPagerScroller mScroller;

    public EnhancedViewPager(Context context) {
        this(context, null);
    }

    public EnhancedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = viewpager.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            mScroller = new ViewPagerScroller(getContext(), (Interpolator) interpolator.get(null));
            scroller.set(this, mScroller);
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    /**
     * 设置该值后，具体速度为正常速度的1/scrollFactor倍
     *
     * @param scrollFactor 滑动因子
     */
    public void setScrollDurationFactor(float scrollFactor) {
        mScroller.setScrollDurationFactor(scrollFactor);
    }
}
