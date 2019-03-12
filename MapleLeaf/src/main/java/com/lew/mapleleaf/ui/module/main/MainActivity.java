package com.lew.mapleleaf.ui.module.main;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.databinding.ActivityMainBinding;
import com.lew.mapleleaf.ui.BaseActivity;
import com.lew.mapleleaf.ui.module.dagger.DaggerActivity;
import com.lew.mapleleaf.ui.module.home.MainActivityContract;
import com.lew.mapleleaf.ui.module.home.MainPresenter;
import com.lew.mapleleaf.utils.logger.Logger;

import java.lang.ref.WeakReference;

public class MainActivity extends BaseActivity<MainPresenter, ActivityMainBinding> implements MainActivityContract.View, NavigationView.OnNavigationItemSelectedListener {
    private Handler mHandler;
    private static int mSelectedItem = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initDrawerLayout();
        initMainPage();
    }

    private void initMainPage() {
        MainPager pager = new MainPager(getSupportFragmentManager());
        mViewBinding.viewPager.setAdapter(pager);
        mViewBinding.tabLayout.setupWithViewPager(mViewBinding.viewPager);
    }

    private void initDrawerLayout() {
        mHandler = new MainHandler(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //将侧边栏顶部延伸至status bar
            mViewBinding.drawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            mViewBinding.drawerLayout.setClipToPadding(false);
        }
        mViewBinding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                mHandler.sendEmptyMessage(mSelectedItem);
            }
        });
        mViewBinding.navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mSelectedItem = item.getItemId();
        switch (item.getItemId()) {
            case R.id.nav_news:
                Logger.e(TAG, "nav_news");
                mViewBinding.drawerLayout.closeDrawer(Gravity.START);
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

    private static class MainHandler extends Handler {
        private WeakReference<MainActivity> reference;

        private MainHandler(MainActivity context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = reference.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case R.id.nav_news:
                    activity.startDaggerActivity();
                    break;
            }
            mSelectedItem = -1;
        }
    }

    private class MainPager extends FragmentStatePagerAdapter {

        private SparseArray<MainDetailFragment> fragments = new SparseArray<>();
        private String[] title;

        private MainPager(FragmentManager fm) {
            super(fm);
            title = getResources().getStringArray(R.array.main_page_title);
            fragments.put(0, MainDetailFragment.newInstance(1));
            fragments.put(1, MainDetailFragment.newInstance(2));
            fragments.put(2, MainDetailFragment.newInstance(3));
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }

    private void startDaggerActivity() {
        Intent intent = new Intent(MainActivity.this, DaggerActivity.class);
        startActivity(intent);
    }
}
