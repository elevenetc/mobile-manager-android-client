package su.elevenets.devicemanagerclient.managers;

import rx.Observable;

/**
 * Created by eleven on 28/08/2016.
 */
public interface AppManager {
	int getSenderId();

	String getManufacturer();

	String getModel();

	String getDeviceId();

	Observable<String> getGcmToken();
}