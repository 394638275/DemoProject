package com.lew.mapleleaf.ui.module.dagger;

import dagger.Component;

/**
 * Created by Richie 2017/4/11.
 */
@Component(modules = FlowerModule.class)
public interface DaggerActivityComponent {
    void inject(DaggerActivity activity);
}
