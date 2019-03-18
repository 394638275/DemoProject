package com.lew.mapleleaf.ui.widgets.autoscrollpager;

import java.util.ArrayList;

public abstract class AutoScrollPagerAdapter extends IBindView {
    private ArrayList<OnChangeListener> listeners = new ArrayList<>();

    public void addOnChangeListener(OnChangeListener listener) {
        listeners.add(listener);
    }

    public void notifyDataSetChanged() {
        synchronized (this) {
            for (OnChangeListener listener : listeners) {
                listener.onChange();
            }
        }
    }
}
