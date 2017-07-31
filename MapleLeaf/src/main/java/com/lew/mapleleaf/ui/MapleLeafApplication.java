package com.lew.mapleleaf.ui;

import android.app.Application;
import android.os.StrictMode;

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
        setStrictMode();
    }

    private void setStrictMode() {
//        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                    .detectDiskReads()
//                    .detectDiskWrites()
//                    .detectNetwork()// or .detectAll() for all detectable problems
//                    .detectCustomSlowCalls()
//                    .detectResourceMismatches()
                    .detectAll()
                    .penaltyFlashScreen()
                    .penaltyDialog()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .detectLeakedRegistrationObjects()
                    .detectActivityLeaks()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
//        }
    }

    /**
     * 初始化日志工具
     */
    private void initLogger() {
        Logger.init().setLogLevel(BuildConfig.LOG_DEBUG ? Logger.LogLevel.FULL : Logger.LogLevel.NONE);
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
