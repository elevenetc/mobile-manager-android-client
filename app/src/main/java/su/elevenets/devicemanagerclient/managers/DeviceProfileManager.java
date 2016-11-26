package su.elevenets.devicemanagerclient.managers;

import rx.Observable;
import rx.Single;
import su.elevenets.devicemanagerclient.models.DeviceProfile;

/**
 * Created by eugene.levenetc on 14/11/2016.
 */
public interface DeviceProfileManager {
	Observable<Object> uploadDeviceProfile();

	Single<Object> updateOnlineState();

	Observable<DeviceProfile> getDeviceProfile();

	void subscribeOnDeviceEvents();
}
