package su.elevenets.devicemanagerclient.managers;

import rx.Completable;

/**
 * Created by Eugene Levenetc on 23/07/2016.
 */
public interface ServicesManager {
	Completable sendGCMToken();

	Completable clearGCMToken();
}
