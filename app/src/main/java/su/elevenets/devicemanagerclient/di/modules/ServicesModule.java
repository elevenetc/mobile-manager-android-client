package su.elevenets.devicemanagerclient.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import su.elevenets.devicemanagerclient.managers.RestManager;
import su.elevenets.devicemanagerclient.managers.ServicesManager;
import su.elevenets.devicemanagerclient.managers.ServicesManagerImpl;
import su.elevenets.devicemanagerclient.managers.SettingsManager;

import javax.inject.Singleton;

/**
 * Created by Eugene Levenetc on 23/07/2016.
 */
@Module
public class ServicesModule {

	private Context context;

	public ServicesModule(Context context) {
		this.context = context;
	}

	@Provides @Singleton public ServicesManager provideServicesManager(
			SettingsManager settingsManager,
			RestManager restManager,
			int gcmSenderId
	) {
		return new ServicesManagerImpl(context, settingsManager, restManager, gcmSenderId);
	}
}
