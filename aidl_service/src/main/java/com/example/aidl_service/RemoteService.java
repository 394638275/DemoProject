package com.example.aidl_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.example.aidl.IParticipateCallback;
import com.example.aidl.IRemoteService;

import java.util.ArrayList;
import java.util.List;

/**
 * author: LQ
 * date: 2019/3/13 15:48
 * description:
 */
public class RemoteService extends Service {
    public static final String TAG = RemoteService.class.getSimpleName();
    private List<Client> clients = new ArrayList<>();
    private RemoteCallbackList<IParticipateCallback> mCallbacks = new RemoteCallbackList<>();

    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.d(TAG, "called RemoteService basicTypes()");
        }

        @Override
        public int someOperate(int a, int b) throws RemoteException {
            Log.d(TAG, "called RemoteService someOperate()");
            return a + b;
        }

        @Override
        public void join(IBinder token, String name) throws RemoteException {
            Log.d(TAG, "called RemoteService join()");
            int idx = findClient(token);
            if (idx > 0) {
                Log.d(TAG, "already joined");
                return;
            }

            Client client = new Client(token, name);
            token.linkToDeath(client, 0);
            clients.add(client);

            notifyParticipate(name, true);
        }

        @Override
        public void leave(IBinder token) throws RemoteException {
            Log.d(TAG, "called RemoteService leave()");
            int idx = findClient(token);
            if (idx < 0) {
                Log.d(TAG, "already leave");
                return;
            }

            Client client = clients.get(idx);
            clients.remove(client);
            //取消注册
            client.mToken.unlinkToDeath(client, 0);
            //通知client离开
            notifyParticipate(client.mName, false);
        }

        @Override
        public List<String> getParticipators() throws RemoteException {
            Log.d(TAG, "called RemoteService getParticipators()");
            List<String> names = new ArrayList<>();
            for (Client client : clients) {
                names.add(client.mName);
            }
            return names;
        }

        @Override
        public void registerParticipateCallback(IParticipateCallback cb) throws RemoteException {
            Log.d(TAG, "called RemoteService registerParticipateCallback()");
            mCallbacks.register(cb);
        }

        @Override
        public void unregisterParticipateCallback(IParticipateCallback cb) throws RemoteException {
            Log.d(TAG, "called RemoteService unregisterParticipateCallback()");
            mCallbacks.unregister(cb);
        }
    };

    private int findClient(IBinder token) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).mToken == token) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallbacks.kill();
    }

    private final class Client implements IBinder.DeathRecipient {
        public final IBinder mToken;
        public final String mName;

        public Client(IBinder mToken, String mName) {
            this.mToken = mToken;
            this.mName = mName;
        }

        @Override
        public void binderDied() {
            int index = clients.indexOf(this);
            if (index < 0) {
                return;
            }

            Log.d(TAG, "client dead: " + mName);
            clients.remove(this);

            notifyParticipate(mName, false);
        }
    }

    private void notifyParticipate(String mName, boolean b) {
        final int length = mCallbacks.beginBroadcast();
        try {
            for (int i = 0; i < length; i++) {
                mCallbacks.getBroadcastItem(i).onParticipate(mName, b);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
