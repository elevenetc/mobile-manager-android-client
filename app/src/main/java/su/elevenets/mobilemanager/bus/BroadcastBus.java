package su.elevenets.mobilemanager.bus;

import rx.Observable;

/**
 * Created by eugene.levenetc on 14/11/2016.
 */
public interface BroadcastBus {

	Observable<Object> getObservable();

	void post(Object object);

	<T> Observable<T> subscribeOn(Class<T> type);
}
