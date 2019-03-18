package com.lew.mapleleaf.ui.widgets.autoscrollpager;

import android.content.Context;

/**
 * Created by scott on 15/8/17.
 */
class Util {

    //Dp to Px
    static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
