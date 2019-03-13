package com.lew.mapleleaf.ui.module.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.aidl.IParticipateCallback;
import com.example.aidl.IRemoteService;
import com.lew.mapleleaf.R;
import com.lew.mapleleaf.databinding.ActivityAidlBinding;
import com.lew.mapleleaf.ui.BaseActivity;
import com.lew.mapleleaf.utils.common.ToastUtils;

import java.util.List;
import java.util.Random;

/**
 * author: LQ
 * date: 2019/3/13 15:25
 * description:
 */
public class AIDLActivity extends BaseActivity<AIDLPresenter, ActivityAidlBinding> implements AIDLActivityContract.View, View.OnClickListener {
    private boolean mIsRegistered = false;
    private boolean mIsBound = false;
    private boolean isJoin = false;

    private IBinder mToken = new Binder();
    private Random mRandom = new Random();

    private IRemoteService mService;
    private ArrayAdapter<String> mAdapter;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ToastUtils.show("Service connected");
            mService = IRemoteService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ToastUtils.show("Service disconnected");
            mService = null;
        }
    };

    private IParticipateCallback callback = new IParticipateCallback.Stub() {
        @Override
        public void onParticipate(String name, boolean joinOrLeave) throws RemoteException {
            if (joinOrLeave) {
                mAdapter.add(name);
            } else {
                mAdapter.remove(name);
            }
        }
    };

    @Override
    protected void initView() {
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mViewBinding.list.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aidl;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind:
                Intent intent = new Intent(IRemoteService.class.getSimpleName());
                intent.setClassName("com.example.aidl_service", "com.example.aidl_service.RemoteService");
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                mIsBound = true;
                break;

            case R.id.unbind:
                if (mIsBound) {
                    unbindService(mServiceConnection);
                    mIsBound = false;
                }
                break;

            case R.id.call:
                callRemote();
                break;

            case R.id.join:
                toggleJoin();
                break;

            case R.id.get_participators:
                updateParticipators();
                break;

            case R.id.register_callback:
                tangleRegisterCallback();
                break;
        }

    }

    private void tangleRegisterCallback() {
        if (!isServiceReady()) {
            return;
        }
        try {
            if (mIsRegistered) {
                mService.unregisterParticipateCallback(callback);
                mViewBinding.registerCallback.setText("Register Callback");
                mIsRegistered = true;
            } else {
                mService.registerParticipateCallback(callback);
                mViewBinding.registerCallback.setText("Unregister Callback");
                mIsRegistered = false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void updateParticipators() {
        if (!isServiceReady()) {
            return;
        }

        try {
            List<String> participators = mService.getParticipators();
            mAdapter.clear();
            mAdapter.addAll(participators);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void toggleJoin() {
        if (!isServiceReady()) {
            return;
        }
        try {
            if (!isJoin) {
                String name = "Client: " + mRandom.nextInt(10);
                mService.join(mToken, name);
                mViewBinding.join.setText("leave");
                isJoin = true;
            } else {
                mService.leave(mToken);
                mViewBinding.join.setText("join");
                isJoin = false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void callRemote() {
        if (isServiceReady()) {
            try {
                int result = mService.someOperate(111, 222);
                com.lew.mapleleaf.utils.common.ToastUtils.show("Remote call return: " + result);
            } catch (RemoteException e) {
                e.printStackTrace();
                com.lew.mapleleaf.utils.common.ToastUtils.show("Remote call error!");
            }
        }
    }

    private boolean isServiceReady() {
        if (mService != null) {
            return true;
        } else {
            com.lew.mapleleaf.utils.common.ToastUtils.show("Service is not available yet!");
            return false;
        }
    }
}
