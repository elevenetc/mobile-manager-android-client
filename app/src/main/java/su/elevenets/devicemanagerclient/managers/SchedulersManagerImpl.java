package su.elevenets.devicemanagerclient.managers;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eugene.levenetc on 26/11/2016.
 */
public class SchedulersManagerImpl implements SchedulersManager {
	@Override public Scheduler ui() {
		return AndroidSchedulers.mainThread();
	}

	@Override public Scheduler background() {
		return Schedulers.io();
	}
}
