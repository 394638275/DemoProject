package com.lew.mapleleaf.ui;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.StrictMode;

import com.lew.mapleleaf.BuildConfig;
import com.lew.mapleleaf.db.SQLHelper;
import com.lew.mapleleaf.utils.app.AppIntroUtil;
import com.lew.mapleleaf.utils.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import java.util.Stack;

public class MapleLeafApplication extends Application {
    private static MapleLeafApplication mInstance;
    private SQLHelper sqlHelper;
    private Stack<Activity> mStack;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mStack = new Stack<>();
        LeakCanary.install(this);   //内存泄露检查
        AppIntroUtil.getInstance().markOpenApp(this);   //记录app是否初次启动
        initLogger();
        setStrictMode();
        registerActivityLifecycleCallbacks(new ActivityCallback());
    }

    private class ActivityCallback implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            mStack.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            mStack.remove(activity);
        }
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
        if (sqlHelper == null) {
            sqlHelper = new SQLHelper(mInstance);
        }
        return sqlHelper;
    }

    /**
     * 获取当前Activity
     */
    public Activity getCurrentActivity() {
        return mStack.lastElement();
    }

    @Override
    public void onTerminate() {
        if (sqlHelper != null) {
            sqlHelper.close();
        }
        super.onTerminate();
    }
}
