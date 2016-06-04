package com.lew.mapleleaf.ui.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.beans.SomeOne;
import com.lew.mapleleaf.utils.logger.Logger;

import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter {

    private static final String TAG = ShowAdapter.class.getSimpleName();
    private OnRecyclerViewListener onRecyclerViewListener;
    private List<SomeOne> list;

    public ShowAdapter(List<SomeOne> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Logger.d(TAG, "onCreateViewHolder, i: " + i);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyler_view_item, viewGroup, false);
//        LinearLayout.LayoutParams lp =  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        view.setLayoutParams(lp);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Logger.d(TAG, "onBindViewHolder, i: " + i + ", viewHolder: " + viewHolder);
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        holder.position = i;
        SomeOne person = list.get(i);
        holder.nameTv.setText(person.getName());
        holder.ageTv.setText(person.getAge() + "岁");
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public View rootView;
        public TextView nameTv;
        public TextView ageTv;
        public int position;
        public PersonViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_name_tv);
            ageTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_age_tv);
            rootView = itemView.findViewById(R.id.recycler_view_test_item_person_view);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return onRecyclerViewListener != null && onRecyclerViewListener.onItemLongClick(position);
        }

    }

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    public interface OnRecyclerViewListener {
        void onItemClick(int position);

        boolean onItemLongClick(int position);
    }

}
