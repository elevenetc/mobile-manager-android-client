package su.elevenets.devicemanagerclient.managers;

import android.app.Activity;
import rx.Observable;
import su.elevenets.devicemanagerclient.models.DeviceProfile;

/**
 * Created by eleven on 28/08/2016.
 */
public interface AppManager {

	void initValues(Activity activity);

	float batteryLevel();

	int getScreenWidth();

	int getScreenHeight();

	String osVersion();

	String getWiFiSSID();

	boolean hasNfc();

	boolean hasFingerprint();

	boolean hasBluetooth();

	boolean hasBluetoothLowEnergy();

	int getSenderId();

	String getManufacturer();

	String getModel();

	String getDeviceId();

	Observable<String> getGcmToken();

	boolean isLocationAllowed();
}
