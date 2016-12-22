package su.elevenets.devicemanagerclient.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import su.elevenets.devicemanagerclient.bus.BroadcastBus;
import su.elevenets.devicemanagerclient.bus.BroadcastBusImpl;
import su.elevenets.devicemanagerclient.managers.*;
import su.elevenets.devicemanagerclient.managers.loc.LocManager;
import su.elevenets.devicemanagerclient.managers.loc.LocManagerImpl;
import su.elevenets.devicemanagerclient.utils.Utils;

import javax.inject.Singleton;

/**
 * Created by eleven on 28/08/2016.
 */
@Module
public class AppModule {

	private Context app;
	private LocManager locManager;
	private KeyValueManager keyValueManager;

	public AppModule(Context app) {
		this.app = app;
	}

	public void setLocManager(LocManager locManager) {
		this.locManager = locManager;
	}

	public void setKeyValueManager(KeyValueManager keyValueManager) {
		this.keyValueManager = keyValueManager;
	}

	@Provides @Singleton public AppManager provideAppManager(KeyValueManager keyValueManager, Logger logger) {
		return new AppManagerImpl(app, keyValueManager, logger);
	}

	@Provides @Singleton public KeyValueManager provideKeyValueManager() {
		if(keyValueManager != null) return keyValueManager;
		else return new KeyValueManagerImpl(app);
	}

	@Provides @Singleton public LocManager provideLocManager() {
		if(locManager != null) return locManager;
		return new LocManagerImpl(app);
	}

	@Provides @Singleton public BroadcastBus provideProadcastBus() {
		return new BroadcastBusImpl();
	}

	@Provides @Singleton public SchedulersManager provideSchedulersManager() {
		return new SchedulersManagerImpl();
	}

	@Provides @Singleton public DeviceProfileManager provideDeviceProfileManager(
			RestManager restManager,
			LocManager locManager,
			AppManager appManager,
			BroadcastBus broadcastBus,
			SchedulersManager schedulersManager,
			KeyValueManager keyValueManager,
	        Logger logger
	) {
		final DeviceProfileManagerImpl result = new DeviceProfileManagerImpl(
				restManager,
				appManager,
				locManager,
				broadcastBus,
				schedulersManager,
				keyValueManager,
				logger
		);
		result.subscribeOnDeviceEvents();
		return result;
	}

	@Provides @Singleton public Logger provideLogger() {
		return new LoggerImpl(Utils.getAppName(app));
	}
}
