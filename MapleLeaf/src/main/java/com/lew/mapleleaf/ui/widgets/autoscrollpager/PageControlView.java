package com.lew.mapleleaf.ui.widgets.autoscrollpager;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lew.mapleleaf.R;


/**
 * 页面控制器实现
 */
public class PageControlView extends PageControlBase<RecyclerView> {
    private RecyclerView mContainerView;
    private Context mContext;
    private RecyclerView.Adapter mAdapter;
    private Adapter mRealAdapter;

    public PageControlView(Context context) {
        mContext = context;
    }

    @Override
    public void setCurrPage(int currPage) {
        if (null != mRealAdapter) {
            mRealAdapter.setCurrPosition(currPage);
        }
    }

    @Override
    public void setHideForSinglePage(boolean hideForSinglePage) {
        this.hideForSinglePage = hideForSinglePage;
    }

    @Override
    public void setIndicatorSpace(float indicatorSpace) {
        mIndictorSpace = indicatorSpace;
        mContainerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.set((int) (mIndictorSpace / 2), 0, (int) (mIndictorSpace / 2), 0);
            }
        });
    }

    @Override
    public RecyclerView containerView() {
        if (null == mContainerView) {
            mContainerView = new RecyclerView(mContext);
            mContainerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
            mContainerView.setId(R.id.container_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            mContainerView.setLayoutManager(linearLayoutManager);
        }
        return mContainerView;
    }

    @Override
    public void setAdapter(final Adapter adapter) {
        if (null != adapter) {
            mAdapter = new RecyclerView.Adapter() {
                @NonNull
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    ViewHolder viewHolder = adapter.onCreateViewHolder(parent, viewType);
                    return new RecyclerView.ViewHolder(viewHolder.itemView) {
                        @Override
                        public String toString() {
                            return super.toString();
                        }
                    };
                }

                @Override
                public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (null != onItemClickListener) {
                                onItemClickListener.onItemClick(view, position);
                            }
                        }
                    });
                    adapter.onBindViewHolder(new ViewHolder(holder.itemView), position, adapter.getCurrPosition());
                }

                @Override
                public int getItemCount() {
                    return adapter.getItemCount();
                }
            };
            adapter.setPageControl(this);
            mRealAdapter = adapter;
            containerView().setAdapter(mAdapter);
        }
    }

    @Override
    public void notifyDatasetChanged() {
        if (null != mAdapter) mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setVisible(boolean visible) {
        containerView().setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean isVisible() {
        return containerView().getVisibility() == View.VISIBLE;
    }
}
