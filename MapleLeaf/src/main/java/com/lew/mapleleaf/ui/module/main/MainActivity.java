package com.lew.mapleleaf.ui.module.main;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.WindowManager;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.BaseActivity;
import com.lew.mapleleaf.ui.module.dagger.DaggerActivity;
import com.lew.mapleleaf.utils.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initDrawerLayout(mDrawerLayout, mNavigationView);
        addFragment(R.id.fl_container, new MainFragment());
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initDrawerLayout(DrawerLayout drawerLayout, NavigationView navView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //将侧边栏顶部延伸至status bar
            drawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            drawerLayout.setClipToPadding(false);
        }
//        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                mHandler.sendEmptyMessage(mItemId);
//            }
//        });
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_news:
                Logger.e(TAG, "nav_news");
                mDrawerLayout.closeDrawer(Gravity.START);
                Intent intent = new Intent(MainActivity.this, DaggerActivity.class);
                startActivity(intent);
                return true;

            case R.id.nav_photos:
                Logger.e(TAG, "nav_photos");
                return true;

            case R.id.nav_videos:
                Logger.e(TAG, "nav_videos");
                return true;

            case R.id.nav_setting:
                Logger.e(TAG, "nav_setting");
                return true;

            default:
                Logger.e(TAG, "default");
                return super.onOptionsItemSelected(item);
        }
    }
}
