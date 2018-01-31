package com.lew.mapleleaf.ui.module.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lew.mapleleaf.utils.logger.Logger;

/**
 * Created by liuqian 16/7/24.
 */
public class RemoteService extends Service{
    private static final String TAG = "RemoteService";
    private MyServiceConnection conn;

    @Override
    public void onCreate() {
        super.onCreate();
        if (conn == null){
            conn = new MyServiceConnection();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 链接远程服务
        this.bindService(new Intent(this, LocalService.class), conn, BIND_IMPORTANT);
        return START_STICKY; //异常重启
    }

    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 建立连接
            Logger.e(TAG, "守护进程开启");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 断开连接
            Logger.e(TAG, "守护进程断开");
            Intent guardIntent = new Intent(RemoteService.this, LocalService.class);

            RemoteService.this.startService(guardIntent);
            RemoteService.this.bindService(guardIntent, conn, BIND_IMPORTANT);
        }
    }

}
