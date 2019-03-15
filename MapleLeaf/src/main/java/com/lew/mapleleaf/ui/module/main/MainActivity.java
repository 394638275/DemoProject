package com.lew.mapleleaf.ui.module.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MenuItem;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.databinding.ActivityMainBinding;
import com.lew.mapleleaf.ui.BaseActivity;
import com.lew.mapleleaf.ui.module.aidl.AIDLActivity;
import com.lew.mapleleaf.ui.module.dagger.DaggerActivity;
import com.lew.mapleleaf.ui.module.home.MainActivityContract;
import com.lew.mapleleaf.ui.module.home.MainPresenter;
import com.lew.mapleleaf.utils.logger.Logger;

public class MainActivity extends BaseActivity<MainPresenter, ActivityMainBinding> implements MainActivityContract.View, NavigationView.OnNavigationItemSelectedListener {
//    private Handler mHandler;
//    private static int mSelectedItem = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
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
//        mHandler = new MainHandler(this);
//        mViewBinding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                mHandler.sendEmptyMessage(mSelectedItem);
//            }
//        });
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mViewBinding.drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.syncState();
        mViewBinding.drawerLayout.addDrawerListener(drawerToggle);
        mViewBinding.navView.setNavigationItemSelectedListener(this);
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerToggle.setDrawerSlideAnimationEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mViewBinding.drawerLayout.openDrawer(Gravity.START);
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        mSelectedItem = item.getItemId();
        switch (item.getItemId()) {
            case R.id.nav_news:
                Logger.d(TAG, "nav_news");
                mViewBinding.drawerLayout.closeDrawer(Gravity.START);
                startDaggerActivity();
                return true;

            case R.id.nav_photos:
                Logger.d(TAG, "nav_photos");
                mViewBinding.drawerLayout.closeDrawer(Gravity.START);
                startAidlActivity();
                return true;

            case R.id.nav_videos:
                Logger.d(TAG, "nav_videos");
                return true;

            case R.id.nav_setting:
                Logger.d(TAG, "nav_setting");
                return true;

            default:
                Logger.d(TAG, "default");
                return super.onOptionsItemSelected(item);
        }
    }

    private void startAidlActivity() {
        startActivity(new Intent(this, AIDLActivity.class));
    }

//    private static class MainHandler extends Handler {
//        private WeakReference<MainActivity> reference;
//
//        private MainHandler(MainActivity context) {
//            reference = new WeakReference<>(context);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            MainActivity activity = reference.get();
//            if (activity == null) {
//                return;
//            }
//            switch (msg.what) {
//                case R.id.nav_news:
//                    activity.startDaggerActivity();
//                    break;
//                case R.id.nav_photos:
//                    activity.startAidlActivity();
//                    break;
//            }
//            mSelectedItem = -1;
//        }
//    }

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
