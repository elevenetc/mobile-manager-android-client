package su.elevenets.devicemanagerclient.utils;

import rx.functions.Action1;

/**
 * Created by eugene.levenetc on 26/11/2016.
 */
public class Actions {

	public static Action1<Throwable> error() {
		return new Action1<Throwable>() {
			@Override public void call(Throwable throwable) {
				throwable.printStackTrace();
			}
		};
	}

	public static <T> Action1<T> empty() {
		return new Action1<T>() {
			@Override public void call(T o) {

			}
		};
	}
}
