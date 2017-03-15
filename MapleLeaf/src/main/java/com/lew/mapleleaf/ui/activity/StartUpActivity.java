package com.lew.mapleleaf.ui.activity;

import android.content.Intent;
import android.os.Handler;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.BaseActivity;
import com.lew.mapleleaf.utils.app.AppIntroUtil;
import com.lew.mapleleaf.utils.logger.Logger;

/**
 * Created by richie 2016/3/31.
 */
public class StartUpActivity extends BaseActivity {

    @Override
    protected int addContentRes() {
        return R.layout.activity_start_up;
    }

    @Override
    protected void initView()  {

    }

    @Override
    protected void initData() {
        int launchMode = AppIntroUtil.getInstance().getLaunchMode();
        //自己的控制逻辑
        if (launchMode == AppIntroUtil.MODE_NEW_INSTALL) {
            Logger.i("mode_new_install");
        } else if (launchMode == AppIntroUtil.MODE_UPDATE) {
            Logger.i("mode_update");
        } else if (launchMode == AppIntroUtil.MODE_AGAIN) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(StartUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
            Logger.i("mode_again");
        }
    }
}
