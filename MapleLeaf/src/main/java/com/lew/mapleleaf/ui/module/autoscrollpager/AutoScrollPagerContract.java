package com.lew.mapleleaf.ui.module.autoscrollpager;

import com.lew.mapleleaf.base.BasePresenter;
import com.lew.mapleleaf.base.BaseView;

/**
 * author: LQ
 * date: 2019/3/8 20:08
 * description:
 */
public interface AutoScrollPagerContract {
    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<AutoScrollPagerContract.View> {
      
    }
}
