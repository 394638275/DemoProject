package com.lew.mapleleaf.ui.module.main;

import com.app.annotations.apt.InstanceFactory;
import com.lew.mapleleaf.ui.module.splash.SplashContract;
import com.lew.mapleleaf.utils.rx.RxHelper;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

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
        Disposable subscribe = RxHelper.countdown(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                mView.setCountDown(integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                mView.doSkip();
            }
        });
        mCompositeSubscription.add(subscribe);
    }

}
