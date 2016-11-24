package su.elevenets.devicemanagerclient.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import su.elevenets.devicemanagerclient.bus.BroadcastBus;
import su.elevenets.devicemanagerclient.bus.BroadcastBusImpl;
import su.elevenets.devicemanagerclient.managers.*;

import javax.inject.Singleton;

/**
 * Created by eleven on 28/08/2016.
 */
@Module
public class AppModule {

	public static final String GCM_TOKEN = "pushToken";

	private Context app;

	public AppModule(Context app) {
		this.app = app;
	}

	@Provides
	@Singleton
	public AppManager provideAppManager(KeyValueManager keyValueManager) {
		return new AppManagerImpl(app, keyValueManager);
	}

	@Provides
	@Singleton
	public KeyValueManager provideKeyValueManager() {
		return new KeyValueManagerImpl(app);
	}

	@Provides
	@Singleton
	public LocManager provideLocManager() {
		return new LocManagerImpl(app);
	}

	@Provides
	@Singleton
	public BroadcastBus provideProadcastBus() {
		return new BroadcastBusImpl();
	}

	@Provides
	@Singleton
	public DeviceProfileManager provideDeviceProfileManager(RestManager restManager, LocManager locManager, AppManager appManager) {
		return new DeviceProfileManagerImpl(restManager, appManager, locManager);
	}
}
