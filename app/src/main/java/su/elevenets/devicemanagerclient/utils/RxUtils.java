package su.elevenets.devicemanagerclient.utils;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by eleven on 28/08/2016.
 */
public class RxUtils {
	public static <T> void onError(Subscriber<T> subscriber, Throwable throwable) {
		if (!subscriber.isUnsubscribed()) subscriber.onError(throwable);
	}

	public static <T> void onNext(Subscriber<T> subscriber, T result) {
		if (!subscriber.isUnsubscribed()) subscriber.onNext(result);
	}

	public static void unsub(Subscription subscription) {
		if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
	}

}
