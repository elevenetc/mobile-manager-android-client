package su.elevenets.devicemanagerclient.di.modules;

import dagger.Module;
import dagger.Provides;
import su.elevenets.devicemanagerclient.managers.KeyValueManager;
import su.elevenets.devicemanagerclient.managers.RestManager;
import su.elevenets.devicemanagerclient.managers.RestManagerImpl;

import javax.inject.Singleton;

/**
 * Created by Eugene Levenetc on 23/07/2016.
 */
@Module
public class RestModule {
	@Provides @Singleton public RestManager provideRestManager(KeyValueManager keyValueManager) {
		return new RestManagerImpl(keyValueManager);
	}
}
