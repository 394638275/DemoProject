package com.lew.mapleleaf.ui;

import android.app.Application;

import com.lew.mapleleaf.BuildConfig;
import com.lew.mapleleaf.db.SQLHelper;
import com.lew.mapleleaf.utils.app.AppIntroUtil;
import com.lew.mapleleaf.utils.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

public class MapleLeafApplication extends Application {
    private static MapleLeafApplication mInstance;
    private SQLHelper sqlHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        LeakCanary.install(this);   //内存泄露检查
        AppIntroUtil.getInstance().markOpenApp(this);   //记录app是否初次启动
        initLogger();
    }

    /**
     * 初始化日志工具
     */
    private void initLogger() {
        Logger.init().setLogLevel(BuildConfig.DEBUG ? Logger.LogLevel.FULL : Logger.LogLevel.NONE);
    }

    /**
     * 获取Application
     */
    public static MapleLeafApplication getInstance() {
        return mInstance;
    }

    /**
     * 获取数据库Helper
     */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(mInstance);
        return sqlHelper;
    }

    @Override
    public void onTerminate() {
        if (sqlHelper != null) {
            sqlHelper.close();
        }
        super.onTerminate();
    }
}
