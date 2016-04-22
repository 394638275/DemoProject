package com.lew.mapleleaf.utils.common;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.base.MapleLeafApplication;


public class ToastUtils {

    private static MapleLeafToast mInstance;

    public static void show(int resId) {
        show(resId, Toast.LENGTH_LONG);
    }

    public static void show(int resId, int duration) {
        show(resId, duration, null);
    }

    public static void show(int resId, Handler handler) {
        show(resId, Toast.LENGTH_LONG, handler);
    }

    public static void show(int resId, int duration, Handler handler) {
        show(MapleLeafApplication.getInstance().getString(resId), duration, handler);
    }

    public static void show(String content) {
        show(content, Toast.LENGTH_LONG);
    }

    public static void show(String content, int duration) {
        show(content, duration, null);
    }

    public static void show(String content, Handler handler) {
        show(content, Toast.LENGTH_LONG, handler);
    }

    public static void show(final String content, final int duration, final Handler handler) {
        if (handler == null) {
            executeShow(content, duration);
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    executeShow(content, duration);
                }
            });
        }
    }

    private static void executeShow(String content, int duration) {
        if (mInstance == null) {
            mInstance = new MapleLeafToast(MapleLeafApplication.getInstance());
        }
        mInstance.setText(content);
        mInstance.setDuration(duration);
        if (!TextUtils.isEmpty(content == null ? "" : content.trim())) {
            mInstance.show();
        }
    }

    private static class MapleLeafToast extends Toast {
        private TextView mTipTv;

        public MapleLeafToast(Context context) {
            super(context);
            View view = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
            mTipTv = (TextView) view.findViewById(R.id.tv_toast);
            setGravity(Gravity.CENTER, 0, 0);
            setView(view);
        }

        @Override
        public void setText(CharSequence s) {
            mTipTv.setText(s);
        }

        @Override
        public void setText(int resId) {
            mTipTv.setText(resId);
        }
    }
}
