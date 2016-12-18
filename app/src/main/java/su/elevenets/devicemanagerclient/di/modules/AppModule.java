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

	public AppModule(Context app) {
		this.app = app;
	}

	@Provides @Singleton public AppManager provideAppManager(KeyValueManager keyValueManager) {
		return new AppManagerImpl(app, keyValueManager);
	}

	@Provides @Singleton public KeyValueManager provideKeyValueManager() {
		return new KeyValueManagerImpl(app);
	}

	@Provides @Singleton public LocManager provideLocManager() {
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
