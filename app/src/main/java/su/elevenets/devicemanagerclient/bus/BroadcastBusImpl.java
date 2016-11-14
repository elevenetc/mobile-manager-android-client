package su.elevenets.devicemanagerclient.bus;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by eugene.levenetc on 14/11/2016.
 */
public class BroadcastBusImpl implements BroadcastBus {

    private PublishSubject<Object> subject = PublishSubject.create();

    @Override
    public Observable getObservable() {
        return subject.asObservable();
    }

    @Override
    public void post(Object object) {
        subject.onNext(object);
    }
}
