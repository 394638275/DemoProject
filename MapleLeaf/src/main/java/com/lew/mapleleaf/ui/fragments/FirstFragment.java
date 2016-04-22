package com.lew.mapleleaf.ui.fragments;

import android.widget.GridView;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.base.BaseFragment;
import com.lew.mapleleaf.utils.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends BaseFragment {
    private GridView gridView;
    private ShowAdapter showAdapter;
    private List<String> mList;

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {
        gridView = findView(R.id.gridview);
    }

    @Override
    protected void lazyLoadData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mList.add("show " + i);
        }
        showAdapter = new ShowAdapter(getActivity(), mList);
        gridView.setAdapter(showAdapter);
        Logger.d(getClass().getSimpleName(), "lazyLoadData");
    }

    @Override
    protected void initData() {
        Logger.d(getClass().getSimpleName(), "initData");
    }
}
