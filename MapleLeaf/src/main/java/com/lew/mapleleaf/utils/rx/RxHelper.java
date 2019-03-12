package com.lew.mapleleaf.utils.rx;

import java.util.concurrent.TimeUnit;

import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Richie 2017/4/7.
 */

public class RxHelper {

    private RxHelper() {
        throw new AssertionError();
    }

    private static final FlowableTransformer<?, ?> mTransformer = (FlowableTransformer<Object, Object>) upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> io_main() {
        return (FlowableTransformer<T, T>) mTransformer;
    }

    /**
     * 倒计时
     *
     * @param time 倒计时时间
     */
    public static Observable<Integer> countdown(int time) {
        if (time < 0) {
            time = 0;
        }
        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(aLong -> countTime - aLong.intValue())
                .take(countTime + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
