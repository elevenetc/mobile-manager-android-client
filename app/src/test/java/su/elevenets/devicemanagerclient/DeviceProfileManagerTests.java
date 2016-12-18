package su.elevenets.devicemanagerclient;

import org.junit.Before;
import org.junit.Test;
import rx.Single;
import rx.functions.Action1;
import su.elevenets.devicemanagerclient.bus.BroadcastBus;
import su.elevenets.devicemanagerclient.bus.BroadcastBusImpl;
import su.elevenets.devicemanagerclient.bus.events.DeviceBootEvent;
import su.elevenets.devicemanagerclient.bus.events.NetworkChangedEvent;
import su.elevenets.devicemanagerclient.bus.events.PingEvent;
import su.elevenets.devicemanagerclient.consts.Key;
import su.elevenets.devicemanagerclient.managers.*;
import su.elevenets.devicemanagerclient.managers.loc.Loc;
import su.elevenets.devicemanagerclient.managers.loc.LocManager;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static rx.Observable.just;

/**
 * Created by eugene.levenetc on 26/11/2016.
 */
public class DeviceProfileManagerTests {

	RestManager restManager;
	AppManager appManager;
	LocManager locManager;
	BroadcastBus broadcastBus;
	RestManager.Api api;
	DeviceProfileManager deviceProfileManager;
	KeyValueManager keyValueManager;
	Logger logger;

	@Before public void before() {
		restManager = mock(RestManager.class);
		appManager = mock(AppManager.class);
		locManager = mock(LocManager.class);
		keyValueManager = mock(KeyValueManager.class);
		logger = mock(Logger.class);
		broadcastBus = new BroadcastBusImpl();
		api = mock(RestManager.Api.class);
		SchedulersManager schedulersManager = new TestScheduler();

		when(keyValueManager.getBoolean(Key.BOUND)).thenReturn(true);

		//Mockito.when(broadcastBus.subscribeOn(Mockito.any())).thenReturn(Observable.empty());

		deviceProfileManager = new DeviceProfileManagerImpl(
				restManager,
				appManager,
				locManager,
				broadcastBus,
				schedulersManager,
				keyValueManager,
				logger
		);
	}

	@Test public void uploadDevice() {

		final Action1<Object> uploadHandler = mock(Action1.class);
		final Object result = new Object();

		configDefaultSuccessValues(result);

		deviceProfileManager.uploadDeviceProfile().subscribe(uploadHandler);

		verify(uploadHandler, times(1)).call(result);
	}

	@Test public void handleNetworkStateEventAndPostDevice() {

		configDefaultSuccessValues(new Object());

		deviceProfileManager.subscribeOnDeviceEvents();
		broadcastBus.post(new NetworkChangedEvent());

		verify(api, times(1)).postDevice(any());
	}

	@Test public void handleBootEventAndPostDevice() {

		configDefaultSuccessValues(new Object());

		deviceProfileManager.subscribeOnDeviceEvents();
		broadcastBus.post(new DeviceBootEvent());

		verify(api, times(1)).postDevice(any());
	}

	@Test public void handlePingAndPostDevice() {

		configDefaultSuccessValues(new Object());

		deviceProfileManager.subscribeOnDeviceEvents();
		broadcastBus.post(new PingEvent());

		verify(api, times(1)).postDevice(any());
	}


	private void configDefaultSuccessValues(Object postDeviceResult) {
		when(appManager.getGcmToken()).thenReturn(just(""));
		when(appManager.isConnectedToNetwork()).thenReturn(true);
		when(restManager.getApi()).thenReturn(api);
		when(locManager.getLocation()).thenReturn(Single.just(new Loc(0, 0)));
		when(api.postDevice(any())).thenReturn(just(postDeviceResult));
		when(api.updateLocation(anyString(), anyDouble(), anyDouble())).thenReturn(Single.just(new Object()));
	}

}
