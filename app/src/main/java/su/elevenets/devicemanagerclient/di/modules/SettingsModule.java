package su.elevenets.devicemanagerclient.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import su.elevenets.devicemanagerclient.managers.SettingsManager;
import su.elevenets.devicemanagerclient.managers.SettingsManagerImpl;

import javax.inject.Singleton;

/**
 * Created by Eugene Levenetc on 23/07/2016.
 */
@Module
public class SettingsModule {
	private Context context;

	public SettingsModule(Context context) {
		this.context = context;
	}

	@Singleton @Provides public SettingsManager provideSettingsManager() {
		return new SettingsManagerImpl(context);
	}
}
