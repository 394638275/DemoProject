package com.lew.mapleleaf.ui.fragments;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.beans.SomeOne;
import com.lew.mapleleaf.ui.BaseFragment;
import com.lew.mapleleaf.ui.widgets.recylerview.DividerItemDecoration;
import com.lew.mapleleaf.utils.common.ToastUtils;
import com.lew.mapleleaf.utils.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends BaseFragment implements ShowAdapter.OnRecyclerViewListener{
    private RecyclerView mRecyclerView;
    private ShowAdapter showAdapter;
    private List<SomeOne> personList = new ArrayList<>();

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {
        mRecyclerView = findView(R.id.recycler_view);
    }

    @Override
    protected void lazyLoadData() {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        showAdapter = new ShowAdapter(personList);
        showAdapter.setOnRecyclerViewListener(this);
        mRecyclerView.setAdapter(showAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                personList.add(0, new SomeOne("新加入的", 30));
                showAdapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 30; i++) {
            personList.add(new SomeOne("二愣子" + i, i + 20));
        }
    }

    @Override
    public void onItemClick(int position) {
        ToastUtils.show(personList.get(position).getName());
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }
}
