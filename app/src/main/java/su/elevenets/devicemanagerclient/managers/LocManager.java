package su.elevenets.devicemanagerclient.managers;

import android.location.Location;
import rx.Single;

/**
 * Created by eugene.levenetc on 23/11/2016.
 */
public interface LocManager {
	Single<Location> getLocation();
}
