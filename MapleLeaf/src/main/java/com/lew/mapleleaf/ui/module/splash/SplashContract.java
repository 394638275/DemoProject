package com.lew.mapleleaf.ui.module.splash;

import com.lew.mapleleaf.base.BasePresenter;
import com.lew.mapleleaf.base.BaseView;

/**
 * author: LQ
 * date: 2019/3/8 19:26
 * description:
 */
public interface SplashContract {
    interface View extends BaseView {
        void setCountDown(Integer integer);

        void doSkip();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void startCountDown();
    }
}
