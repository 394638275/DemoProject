package com.lew.mapleleaf.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.BaseActivity;
import com.lew.mapleleaf.ui.fragments.AndroidDesignFragment;
import com.lew.mapleleaf.ui.fragments.MainPageFragment;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends BaseActivity {
    private FrameLayout mContainer;
    private RadioGroup mBottomBar;
    private RadioButton mMainButton;
    private MainPageFragment mMainPage;
    private AndroidDesignFragment mDesign;

    @Override
    protected void setTitle() {
        mTitleBar.setTitle(R.string.app_name);
    }

    @Override
    protected int addContentRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mContainer = (FrameLayout) findViewById(R.id.fragment_container);
        mBottomBar = (RadioGroup) findViewById(R.id.rg_tab_menu);
        mMainButton = (RadioButton) findViewById(R.id.rb_tab_main_page);

        mBottomBar.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_tab_main_page:
                        if (mMainPage == null) {
                            mMainPage = new MainPageFragment();
                        }
                        replaceFragment(R.id.fragment_container, mMainPage);
                        break;

                    case R.id.rb_tab_discover:
                        if (mDesign == null){
                            mDesign = new AndroidDesignFragment();
                        }
                        replaceFragment(R.id.fragment_container, mDesign);
                        break;

                    case R.id.rb_tab_topic:

                        break;

                    case R.id.rb_tab_mine:

                        break;

                    default:
                        break;
                }
            }
        });

        mMainButton.setChecked(true);
    }

    @Override
    protected void initData() {



    }

    public void replaceFragment(int id_content, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(id_content, fragment);
        transaction.commit();
    }
}
