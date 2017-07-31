package com.lew.mapleleaf.ui.module.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.lew.mapleleaf.utils.logger.Logger;

/**
 * Created by liuqian 16/7/24.
 */
public class LocalService extends Service {
    private static final String TAG = "LocalService";
    private MyServiceConnection conn;
//    private MyServiceBinder mServiceBinder;

    @Override
    public void onCreate() {
        super.onCreate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Logger.e(TAG, "等待接收消息");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        if (conn == null) {
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
        this.bindService(new Intent(this, LocalService.class), conn, Context.BIND_IMPORTANT);
        return START_STICKY; //异常重启
    }

    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 建立连接
            Toast.makeText(LocalService.this, "建立连接", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 断开连接
            Toast.makeText(LocalService.this, "断开连接", Toast.LENGTH_LONG).show();
            Intent guardIntent = new Intent(LocalService.this, RemoteService.class);

            LocalService.this.startService(guardIntent);
            LocalService.this.bindService(guardIntent, conn, BIND_IMPORTANT);
        }
    }

//    private class MyServiceBinder extends ProcessConn{
//
//    }
}
