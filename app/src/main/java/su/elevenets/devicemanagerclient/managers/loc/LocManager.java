package su.elevenets.devicemanagerclient.managers.loc;

import rx.Single;

/**
 * Created by eugene.levenetc on 23/11/2016.
 */
public interface LocManager {
	Single<Loc> getLocation();
}
