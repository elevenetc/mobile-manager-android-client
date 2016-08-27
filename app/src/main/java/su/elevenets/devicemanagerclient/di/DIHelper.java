package su.elevenets.devicemanagerclient.di;

import android.content.Context;
import su.elevenets.devicemanagerclient.di.components.AppComponent;
import su.elevenets.devicemanagerclient.di.components.DaggerAppComponent;
import su.elevenets.devicemanagerclient.di.modules.ServicesModule;
import su.elevenets.devicemanagerclient.di.modules.SettingsModule;

/**
 * Created by eleven on 27/08/2016.
 */
public class DIHelper {

	private static AppComponent appComponent;

	public static void init(Context appContext) {
		appComponent = DaggerAppComponent.builder()
				.settingsModule(new SettingsModule(appContext))
				.servicesModule(new ServicesModule(appContext))
				.build();
	}

	public static AppComponent getAppComponent() {
		return appComponent;
	}
}
