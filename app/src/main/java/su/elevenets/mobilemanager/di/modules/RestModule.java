package su.elevenets.mobilemanager.di.modules;

import dagger.Module;
import dagger.Provides;
import su.elevenets.mobilemanager.managers.KeyValueManager;
import su.elevenets.mobilemanager.managers.RestManager;
import su.elevenets.mobilemanager.managers.RestManagerImpl;

import javax.inject.Singleton;

/**
 * Created by Eugene Levenetc on 23/07/2016.
 */
@Module
public class RestModule {

	private RestManager restManager;

	public RestModule(RestManager restManager) {
		this.restManager = restManager;
	}

	@Provides @Singleton public RestManager provideRestManager(KeyValueManager keyValueManager) {
		if (restManager != null) return restManager;
		else return new RestManagerImpl(keyValueManager);
	}
}
