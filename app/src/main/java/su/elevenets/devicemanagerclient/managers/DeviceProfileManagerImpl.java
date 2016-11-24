package su.elevenets.devicemanagerclient.managers;

import rx.Observable;
import rx.functions.Func1;
import su.elevenets.devicemanagerclient.models.DeviceProfile;


/**
 * Created by eugene.levenetc on 14/11/2016.
 */
public class DeviceProfileManagerImpl implements DeviceProfileManager {

	private RestManager restManager;
	private KeyValueManager keyValueManager;
	private AppManager appManager;

	public DeviceProfileManagerImpl(RestManager restManager, KeyValueManager keyValueManager, AppManager appManager) {
		this.restManager = restManager;
		this.keyValueManager = keyValueManager;
		this.appManager = appManager;
	}

	@Override public Observable<Object> uploadDeviceProfile() {
		return getDeviceProfile().flatMap(new Func1<DeviceProfile, Observable<Object>>() {
			@Override
			public Observable<Object> call(DeviceProfile device) {
				final String endPoint = keyValueManager.get(KeyValueManager.END_POINT);
				return restManager.getApi(endPoint).postDevice(device);
			}
		});
	}

	@Override public Observable<DeviceProfile> getDeviceProfile() {
		return appManager.getGcmToken().map(token -> {
			DeviceProfile device = new DeviceProfile();
			device.pushToken = token;
			device.deviceId = appManager.getDeviceId();
			device.manufacturer = appManager.getManufacturer();
			device.model = appManager.getModel();
			device.osVersion = appManager.osVersion();
			device.batteryLevel = appManager.batteryLevel();
			device.hasBluetooth = appManager.hasBluetooth();
			device.hasBluetoothLowEnergy = appManager.hasBluetoothLowEnergy();
			device.hasFingerprintScanner = appManager.hasFingerprintScanner();
			device.hasNfc = appManager.hasNfc();
			device.wifiSSID = appManager.getWiFiSSID();
			device.screenWidth = appManager.getScreenWidth();
			device.screenHeight = appManager.getScreenHeight();
			return device;
		});
	}
}
