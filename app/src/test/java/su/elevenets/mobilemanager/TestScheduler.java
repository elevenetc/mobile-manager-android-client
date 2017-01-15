package su.elevenets.mobilemanager;

import rx.Scheduler;
import rx.schedulers.Schedulers;
import su.elevenets.mobilemanager.managers.SchedulersManager;

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
