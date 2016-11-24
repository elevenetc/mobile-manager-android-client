package su.elevenets.devicemanagerclient.managers;

import android.location.Location;
import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import su.elevenets.devicemanagerclient.models.DeviceProfile;


/**
 * Created by eugene.levenetc on 14/11/2016.
 */
public class DeviceProfileManagerImpl implements DeviceProfileManager {

	private RestManager restManager;
	private AppManager appManager;
	private LocManager locManager;

	public DeviceProfileManagerImpl(
			RestManager restManager,
			AppManager appManager,
			LocManager locManager
	) {
		this.restManager = restManager;
		this.appManager = appManager;
		this.locManager = locManager;
	}

	@Override public Observable<Object> uploadDeviceProfile() {

		return getDeviceProfile().flatMap(new Func1<DeviceProfile, Observable<Object>>() {
			@Override
			public Observable<Object> call(DeviceProfile device) {
				return restManager.getApi().postDevice(device);
			}
		}).doOnNext(new Action1<Object>() {
			@Override public void call(Object o) {
				updateLocation();
			}
		}).doOnCompleted(new Action0() {
			@Override public void call() {

			}
		});
	}

	private void updateLocation() {
		locManager
				.getLocation()
				.subscribeOn(AndroidSchedulers.mainThread())
				.flatMap(sendLocation()).subscribe(new Action1<Object>() {
			@Override public void call(Object o) {

			}
		}, new Action1<Throwable>() {
			@Override public void call(Throwable throwable) {

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

	private Func1<Location, Single<Object>> sendLocation() {
		return location -> restManager.getApi().updateLocation(
				appManager.getDeviceId(),
				location.getLatitude(),
				location.getLongitude()
		).subscribeOn(Schedulers.io());
	}
}
