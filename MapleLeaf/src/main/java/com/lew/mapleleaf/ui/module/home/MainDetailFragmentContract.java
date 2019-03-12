package com.lew.mapleleaf.ui.module.home;

import com.lew.mapleleaf.base.BasePresenter;
import com.lew.mapleleaf.base.BaseView;

/**
 * author: LQ
 * date: 2019/3/8 20:08
 * description:
 */
public interface MainDetailFragmentContract {
    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<MainDetailFragmentContract.View> {
      
    }
}
