package com.lew.mapleleaf.ui.module.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.BaseFragment;

import butterknife.BindView;

/**
 * Created by Richie 2017/4/10.
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_main_page;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    @Override
    protected void lazyLoadData(boolean isRefresh) {
        MainPager pager = new MainPager(getChildFragmentManager());
        mViewPager.setAdapter(pager);
        mTabLayout.setupWithViewPager(mViewPager);
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
}
