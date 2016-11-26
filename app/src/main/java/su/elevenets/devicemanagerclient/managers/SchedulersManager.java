package su.elevenets.devicemanagerclient.managers;

import rx.Scheduler;

/**
 * Created by eugene.levenetc on 26/11/2016.
 */
public interface SchedulersManager {

	Scheduler ui();

	Scheduler background();

}
