package com.lew.mapleleaf.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.widgets.views.TitleBar;
import com.lew.mapleleaf.utils.app.ActivityManager;

public abstract class BaseActivity extends FragmentActivity {
    protected TitleBar mTitleBar;
    protected ImageView mCollectView;
    protected ViewGroup mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ActivityManager.getInstance().addActivity(this);
        initTitle();
        addContent();
        initView();
        initData();
    }

    private void addContent() {
        mRootView = (ViewGroup) findViewById(R.id.main_content);
        View view = getLayoutInflater().inflate(addContentRes(), mRootView, false);
        mRootView.addView(view);
        int titleBarHeight = mTitleBar.getTitleBarHeight();
        view.setPadding(0, titleBarHeight, 0, 0);
        if (setContentImmersive()) {
            mTitleBar.setBackgroundResource(android.R.color.transparent);
        }
    }

    protected boolean setContentImmersive() {
        return false;
    }

    protected boolean needTitle(){
        return true;
    }

    private void initTitle() {
        if (hasKitKat() && !hasLollipop()) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (hasLollipop()) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        if (needTitle()) {
            mTitleBar.setVisibility(View.VISIBLE);
            mTitleBar.setImmersive(true);
            mTitleBar.setBackgroundColor(Color.parseColor("#64b4ff"));
            mTitleBar.setTitleColor(Color.WHITE);
            mTitleBar.setSubTitleColor(Color.WHITE);
            mTitleBar.setDividerColor(Color.GRAY);
            setTitle();
        } else {
            mTitleBar.setVisibility(View.GONE);
        }
    }

    protected void setTitle() {
        mTitleBar.setLeftImageResource(R.drawable.title_back);
        mTitleBar.setLeftText("返回");
        mTitleBar.setLeftTextColor(Color.WHITE);
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected abstract int addContentRes();

    protected abstract void initView();

    protected abstract void initData();

    protected boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    protected boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().killActivity(this);
    }
}
