package com.lew.mapleleaf.ui.module.main;

import com.app.annotations.apt.InstanceFactory;
import com.lew.mapleleaf.ui.module.splash.SplashContract;
import com.lew.mapleleaf.utils.logger.Logger;
import com.lew.mapleleaf.utils.rx.RxHelper;

import io.reactivex.disposables.Disposable;

/**
 * author: LQ
 * date: 2019/3/8 19:08
 * description:
 */
@InstanceFactory
public class SplashPresenter extends SplashContract.Presenter {
    @Override
    protected void onAttach() {
        startCountDown();
    }

    @Override
    public void startCountDown() {
        Disposable subscribe = RxHelper.countdown(2)
                .subscribe(
                        integer -> mView.setCountDown(integer),
                        throwable -> Logger.e(throwable.getMessage()),
                        () -> mView.doSkip());
        mCompositeSubscription.add(subscribe);
    }

}
