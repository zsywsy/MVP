package com.zsy.mvp1;

import com.zsy.sum.utils.PromptUtils;
import com.zsy.sum.utils.depend.Lg;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaUtils {

//    private static Subscriber<Object> debugSubscriber = new DebugSubscriber<>();

    public static Subscriber<Object> getDebugSubscriber() {
        return new DebugSubscriber<>();
    }

    public static class DebugSubscriber<T> extends Subscriber<T> {

        @Override
        public void onStart() {
            Lg.i("onStart");
        }

        @Override
        public void onCompleted() {
            Lg.i("onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Lg.i("onError");
            Lg.i("onError:" + (e == null ? null : e.getMessage()));
        }

        @Override
        public void onNext(T t) {
            Lg.i("onNext");
            Lg.i("onNext:" + t.toString());
        }
    }


    /**
     * useless,just a try
     *
     * @param <T>
     */
    public static class IOMainTransformer<T> implements Observable.Transformer<T, T> {
        @Override
        public Observable<T> call(Observable<T> observable) {
            return observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


}
