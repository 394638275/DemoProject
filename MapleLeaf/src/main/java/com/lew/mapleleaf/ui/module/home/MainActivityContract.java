package com.lew.mapleleaf.ui.module.home;

import com.lew.mapleleaf.base.BasePresenter;
import com.lew.mapleleaf.base.BaseView;
import com.lew.mapleleaf.ui.module.splash.SplashContract;

/**
 * author: LQ
 * date: 2019/3/8 20:08
 * description:
 */
public interface MainActivityContract {
    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<SplashContract.View> {
      
    }
}
