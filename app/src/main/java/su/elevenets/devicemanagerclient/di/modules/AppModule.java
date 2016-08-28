package su.elevenets.devicemanagerclient.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import su.elevenets.devicemanagerclient.managers.AppManager;
import su.elevenets.devicemanagerclient.managers.AppManagerImpl;
import su.elevenets.devicemanagerclient.managers.KeyValueManager;
import su.elevenets.devicemanagerclient.managers.KeyValueManagerImpl;

import javax.inject.Singleton;

/**
 * Created by eleven on 28/08/2016.
 */
@Module
public class AppModule {

	public static final String GCM_TOKEN = "gcmToken";

	private Context app;

	public AppModule(Context app) {
		this.app = app;
	}

	@Provides @Singleton public AppManager provideAppManager(KeyValueManager keyValueManager){
		return new AppManagerImpl(app, keyValueManager);
	}

	@Provides @Singleton public KeyValueManager provideKeyValueManager(){
		return new KeyValueManagerImpl(app);
	}
}
