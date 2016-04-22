package com.lew.mapleleaf.ui.fragments;

import android.database.SQLException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.beans.ChannelItem;
import com.lew.mapleleaf.beans.ChannelManage;
import com.lew.mapleleaf.db.SQLHelper;
import com.lew.mapleleaf.ui.base.BaseFragment;
import com.lew.mapleleaf.ui.base.MapleLeafApplication;
import com.lew.mapleleaf.ui.widgets.views.ViewPagerIndicator;
import com.lew.mapleleaf.utils.common.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class MainPageFragment extends BaseFragment {
    private HorizontalScrollView mTitle;
    private LinearLayout mTitleContainer;
    private ViewPager mPager;
    private ArrayList<ChannelItem> mUserChannelList = new ArrayList<>();
    private int columnSelectIndex = 0;      //当前选中的栏目
    public View mShadowLeft;   //左阴影部分
    public View mShadowRight;  //右阴影部分
    private ImageButton mMoreColums;
    private RelativeLayout mColum;
    private int mScreenWidth = 0;   //屏幕宽度
    private int mItemWidth = 0;     //Item宽度

    private ViewPagerIndicator indicator;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mList;
    private List<String> mDatas;
    private int itemCount = 3;

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_main_page;
    }

    @Override
    protected void initView() {
        mTitle = findView(R.id.chs_news_keywords_title);
        mTitleContainer = findView(R.id.ll_main_page_title_content);
        mPager = findView(R.id.vp_main_page_news_pager);
        mShadowLeft = findView(R.id.shade_left);
        mShadowRight = findView(R.id.shade_right);
        mMoreColums = findView(R.id.iv_more_columns);
        mColum = findView(R.id.rl_column);
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected void initData() {
//        initColumnData();
        initTabColumn();
        initFragment();
    }

    private void initColumnData() {
        try {
            SQLHelper helper = MapleLeafApplication.getInstance().getSQLHelper();
            mUserChannelList = (ArrayList<ChannelItem>) ChannelManage.getManage(helper).getUserChannel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    private void initTabColumn() {
        mTitleContainer.removeAllViews();
        int count = mUserChannelList.size();
        mScreenWidth = ScreenUtils.getScreenWidth(getActivity());
        mItemWidth = mScreenWidth / 7;
//        mTitle.setParam(getActivity(), mScreenWidth, mTitleContainer, mShadowLeft, mShadowRight, mMoreColums, mColum);
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            // TextView localTextView = (TextView) mInflater.inflate(R.layout.column_radio_item, null);
            TextView columnTextView = new TextView(getActivity());
            columnTextView.setTextAppearance(getActivity(), R.style.top_category_scroll_view_item_text);
            // localTextView.setBackground(getResources().getDrawable(R.drawable.top_category_scroll_text_view_bg));
            columnTextView.setBackgroundResource(R.drawable.radio_button_bg);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(mUserChannelList.get(i).getName());
            columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if (columnSelectIndex == i) {
                columnTextView.setSelected(true);
            }
            columnTextView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mTitleContainer.getChildCount(); i++) {
                        View localView = mTitleContainer.getChildAt(i);
                        if (localView != v) {
                            localView.setSelected(false);
                        } else {
                            localView.setSelected(true);
                            mPager.setCurrentItem(i);
                        }
                    }
                    Toast.makeText(getActivity(), mUserChannelList.get(v.getId()).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            mTitleContainer.addView(columnTextView, i, params);
        }
    }

    private void initFragment() {
        indicator = findView(R.id.indicator);

        mList = new ArrayList<>();
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();
        mList.add(firstFragment);
        mList.add(secondFragment);
        mList.add(thirdFragment);

        mDatas = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            mDatas.add("i=" + i);
        }

        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        };

        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(itemCount);

        //将viewpager与indicator绑定
        indicator.setData(mDatas);
        indicator.setViewPager(mPager);
    }
}
