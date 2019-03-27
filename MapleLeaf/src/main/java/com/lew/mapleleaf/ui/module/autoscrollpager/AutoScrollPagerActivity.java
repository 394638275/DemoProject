package com.lew.mapleleaf.ui.module.autoscrollpager;

import android.view.View;
import android.widget.ImageView;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.databinding.ActivityAutoScrollPagerBinding;
import com.lew.mapleleaf.ui.BaseActivity;
import com.lew.mapleleaf.ui.widgets.autoscrollpager.AutoScrollBase;
import com.lew.mapleleaf.ui.widgets.autoscrollpager.AutoScrollPagerAdapter;
import com.lew.mapleleaf.utils.logger.Logger;

/**
 * author: LQ
 * date: 2019/3/18 16:51
 * description:
 */
public class AutoScrollPagerActivity extends BaseActivity<AutoScrollPagerPresenter, ActivityAutoScrollPagerBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auto_scroll_pager;
    }

    @Override
    protected void initView() {
        mViewBinding.autoScrollPager.setPageControl(new CustomPageControl(this));
        final int[] images = {R.drawable.cat1, R.drawable.cat2};
        mViewBinding.autoScrollPager.setAdapter(new AutoScrollPagerAdapter() {
            @Override
            public void onBindView(View itemView, int position) {
                ((ImageView) itemView).setImageResource(images[position]);
            }

            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public int onLayoutId() {
                return R.layout.view_simple_image;
            }
        });
        ((CustomPageControl) mViewBinding.autoScrollPager.pageControl()).setDescription("当前页面为第1页");
        mViewBinding.autoScrollPager.setOnPageChangeListener(new AutoScrollBase.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((CustomPageControl) mViewBinding.autoScrollPager.pageControl()).setDescription("当前页面为第" + (position + 1) + "页");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mViewBinding.autoScrollPager.setOnItemClickListener(new AutoScrollBase.OnItemClickListener() {
            @Override
            public void onItemClick(int index, View view) {
                Logger.d(TAG, "index: " + index);
            }
        });

        mViewBinding.autoScrollPager.autoScroll();
    }
}
