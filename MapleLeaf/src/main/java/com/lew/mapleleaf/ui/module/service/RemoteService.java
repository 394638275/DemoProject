package com.lew.mapleleaf.ui.module.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by liuqian 16/7/24.
 */
public class RemoteService extends Service{
    private MyServiceConnection conn;
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

    private class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //表示本地服务挂了
//            RemoteService.this.startService(new Intent(RemoteService.this, LocalService.class), conn, BIND_IMPORTANT);
        }
    }

}
