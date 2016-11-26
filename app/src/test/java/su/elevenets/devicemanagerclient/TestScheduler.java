package su.elevenets.devicemanagerclient;

import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import su.elevenets.devicemanagerclient.managers.SchedulersManager;

import java.util.concurrent.TimeUnit;

/**
 * Created by eugene.levenetc on 26/11/2016.
 */
public class TestScheduler implements SchedulersManager{
	@Override public Scheduler ui() {
		return Schedulers.immediate();
	}

	@Override public Scheduler background() {
		return Schedulers.immediate();
	}
}
