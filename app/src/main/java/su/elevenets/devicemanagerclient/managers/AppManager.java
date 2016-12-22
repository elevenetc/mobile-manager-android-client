package su.elevenets.devicemanagerclient.managers;

import android.app.Activity;
import android.support.annotation.Nullable;
import rx.Observable;
import su.elevenets.devicemanagerclient.models.DeviceProfile;

/**
 * Created by eleven on 28/08/2016.
 */
public interface AppManager {

	@Nullable String getSystemProp(String name);

	void initValues(Activity activity);

	boolean isConnectedToNetwork();

	float batteryLevel();

	int getScreenWidth();

	int getScreenHeight();

	double getScreenSize();

	String osVersion();

	String getWiFiSSID();

	boolean hasNfc();

	boolean hasFingerprintScanner();

	boolean hasBluetooth();

	boolean hasBluetoothLowEnergy();

	int getSenderId();

	String getManufacturer();

	String getModel();

	String getDeviceId();

	Observable<String> getGcmToken();

	boolean isLocationAllowed();

	boolean isAndroidM();

	boolean isFingerPrintAccessAllowed();
}
