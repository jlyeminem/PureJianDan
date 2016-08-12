package com.jly.purejiandan.utils;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 *
 * Created by jly on 16-8-12.
 */
public class RxBus {
    private static volatile RxBus defaultInstance;
    private final Subject<Object,Object> bus;

    private RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getDefault() {
        if(defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    public void post(Object o) {
        bus.onNext(o);
    }

    public <T> Observable<T> toObservable (Class<T> eventType) {
        return bus.ofType(eventType);
    }
}
