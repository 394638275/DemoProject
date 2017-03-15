package com.lew.mapleleaf.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.widgets.autoscrollviewpager.AutoScrollViewPager;

public class AutoScrollPagerFragment extends Fragment {

    private List<String> images = new ArrayList<>();
    private AutoScrollViewPager mPager;
    private MyAdapter mAdapter;
    private AutoScrollViewPager.OnPageClickListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auto_scroll_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPager = (AutoScrollViewPager) getActivity().findViewById(R.id.scroll_pager);
//        CirclePageIndicator mIndicator = (CirclePageIndicator) getView().findViewById(R.id.indicator);
        mAdapter = new MyAdapter();
        mPager.setAdapter(mAdapter);
//        mIndicator.setViewPager(mPager);
//        mIndicator.setSnap(true);
        mPager.setScrollFactgor(5);
        mPager.setOffscreenPageLimit(4);
        mPager.startAutoScroll(2000);
        if (mListener != null) {
            mPager.setOnPageClickListener(mListener);
        }
    }
    
    public void setImages(List<String> imageList) {
        this.images = imageList;
    }
    
//    public void updateImages(List<String> imageList) {
//        if (images == null) {
//            images = new ArrayList<String>();
//        }
//        images.clear();
//        images.addAll(imageList);
//        mAdapter.notifyDataSetChanged();
//    }
    
    public void setOnPageClickListener(AutoScrollViewPager.OnPageClickListener onPageClickListener) {
        this.mListener = onPageClickListener;
    }
    
    private class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return images == null ? 0 : images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            ImageLoaderUtils.showImage(images.get(position), view, R.drawable.bg_grey);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
        
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    } 
}
