package com.lew.mapleleaf.ui.module.dagger;

import javax.inject.Inject;

/**
 * Created by Richie 2017/4/11.
 */

public class FlowerCollector {
    private Flower mFlower;

    @Inject
    public FlowerCollector(@RoseType Flower flower) {
        this.mFlower = flower;
    }

    public String showFlowerName() {
        return mFlower.getName();
    }
}
