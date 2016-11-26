package su.elevenets.devicemanagerclient.managers;

import android.location.Location;
import rx.Observable;
import rx.Single;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import su.elevenets.devicemanagerclient.bus.BroadcastBus;
import su.elevenets.devicemanagerclient.bus.events.DeviceBootEvent;
import su.elevenets.devicemanagerclient.bus.events.NetworkChangedEvent;
import su.elevenets.devicemanagerclient.managers.loc.Loc;
import su.elevenets.devicemanagerclient.managers.loc.LocManager;
import su.elevenets.devicemanagerclient.models.DeviceProfile;


/**
 * Created by eugene.levenetc on 14/11/2016.
 */
public class DeviceProfileManagerImpl implements DeviceProfileManager {

	private RestManager restManager;
	private AppManager appManager;
	private LocManager locManager;
	private BroadcastBus broadcastBus;
	private SchedulersManager schedulersManager;

	public DeviceProfileManagerImpl(
			RestManager restManager,
			AppManager appManager,
			LocManager locManager,
			BroadcastBus broadcastBus,
			SchedulersManager schedulersManager
	) {
		this.restManager = restManager;
		this.appManager = appManager;
		this.locManager = locManager;
		this.broadcastBus = broadcastBus;
		this.schedulersManager = schedulersManager;
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

	@Override public Single<Object> updateOnlineState() {
		return restManager.getApi().updateOnlineState(appManager.getDeviceId(), true);
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
			device.screenSize = appManager.getScreenSize();
			return device;
		});
	}

	@Override public void subscribeOnDeviceEvents() {
		broadcastBus
				.subscribeOn(DeviceBootEvent.class)
				.filter(new Func1<DeviceBootEvent, Boolean>() {
					@Override public Boolean call(DeviceBootEvent deviceBootEvent) {
						return appManager.isConnectedToNetwork();
					}
				})
				.flatMap(new Func1<DeviceBootEvent, Observable<?>>() {
					@Override public Observable<?> call(DeviceBootEvent deviceBootEvent) {
						return uploadDeviceProfile();
					}
				})
				.onExceptionResumeNext(Observable.empty())
				.subscribeOn(schedulersManager.background())
				.observeOn(schedulersManager.ui())
				.subscribe(new Action1<Object>() {
					@Override public void call(Object o) {

					}
				}, new Action1<Throwable>() {
					@Override public void call(Throwable throwable) {

					}
				});

		broadcastBus
				.subscribeOn(NetworkChangedEvent.class)
				.filter(event -> appManager.isConnectedToNetwork())
				.flatMap(event -> uploadDeviceProfile())
				.onExceptionResumeNext(Observable.empty())
				.subscribeOn(schedulersManager.background())
				.observeOn(schedulersManager.ui())
				.subscribe();
	}


	private Func1<Loc, Single<Object>> sendLocation() {

		return new Func1<Loc, Single<Object>>() {
			@Override public Single<Object> call(Loc location) {
				return restManager.getApi().updateLocation(
						appManager.getDeviceId(),
						location.getLat(),
						location.getLon()
				).subscribeOn(schedulersManager.background());
			}
		};
	}

	private void updateLocation() {
		locManager
				.getLocation()
				.subscribeOn(schedulersManager.ui())
				.flatMap(sendLocation()).subscribe(new Action1<Object>() {
			@Override public void call(Object o) {

			}
		}, new Action1<Throwable>() {
			@Override public void call(Throwable throwable) {
				throwable.printStackTrace();
			}
		});
	}
}
