package com.lew.mapleleaf.utils.app;

import android.content.Context;
import android.text.TextUtils;

/**
 * 基本功能：app启动引导页控制
 */
public class AppIntroUtil {
    public static final int MODE_NEW_INSTALL = 1;//首次安装启动
    public static final int MODE_UPDATE = 2;    //更新后第一次启动
    public static final int MODE_AGAIN = 3;     //再次启动
    public static final String SHARE_NAME = "lastVersion";
    private boolean isOpenMarked = false;
    private int launchMode = MODE_AGAIN; //启动-模式
    private static AppIntroUtil instance;

    public static AppIntroUtil getInstance() {
        if (instance == null) {
            instance = new AppIntroUtil();
        }
        return instance;
    }

    /**
     * 标记-打开app,用于产生-是否首次打开
     */
    public void markOpenApp(Context context) {
        // 防止-重复调用
        if (isOpenMarked)
            return;
        isOpenMarked = true;
        String lastVersion = SPUtils.getInstance().getString(SHARE_NAME, SHARE_NAME);
        String thisVersion = VersionUtil.getVersion(context);

        if (TextUtils.isEmpty(lastVersion)) {   // 首次启动
            launchMode = MODE_NEW_INSTALL;
            SPUtils.getInstance().putString(SHARE_NAME, thisVersion);
        } else if (VersionUtil.compareVersion(lastVersion, thisVersion)) {  //更新
            launchMode = MODE_UPDATE;
            SPUtils.getInstance().putString(SHARE_NAME, thisVersion);
        } else {    // 二次启动(版本未变)
            launchMode = MODE_AGAIN;
        }
    }

    public int getLaunchMode() {
        return launchMode;
    }

    /**
     * 首次打开,新安装、覆盖(版本号不同)
     */
    public boolean isFirstOpen() {
        return launchMode != MODE_AGAIN;
    }
}
