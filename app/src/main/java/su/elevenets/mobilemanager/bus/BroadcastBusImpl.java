package su.elevenets.mobilemanager.bus;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by eugene.levenetc on 14/11/2016.
 */
public class BroadcastBusImpl implements BroadcastBus {

	private PublishSubject<Object> subject = PublishSubject.create();

	@Override
	public Observable<Object> getObservable() {
		return subject.asObservable();
	}

	@Override
	public void post(Object object) {
		subject.onNext(object);
	}

	@SuppressWarnings("unchecked")
	@Override public <T> Observable<T> subscribeOn(Class<T> type) {
		return (Observable<T>) getObservable().filter(o -> o.getClass().isAssignableFrom(type));
	}
}
