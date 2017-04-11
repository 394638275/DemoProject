package com.lew.mapleleaf.ui.module.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Richie 2017/4/11.
 */
@Module
public class FlowerModule {

    @Provides
    @RoseType
    public Flower createRoseFlower() {
        return new RoseFlower();
    }

    @Provides
    @JasmineType
    public Flower createJasmineFlower(){
        return new JasmineFlower();
    }
}
