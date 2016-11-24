package su.elevenets.devicemanagerclient.di;

import android.content.Context;
import su.elevenets.devicemanagerclient.di.components.AppComponent;
import su.elevenets.devicemanagerclient.di.components.DaggerAppComponent;
import su.elevenets.devicemanagerclient.di.modules.AppModule;
import su.elevenets.devicemanagerclient.di.modules.RestModule;

/**
 * Created by eleven on 27/08/2016.
 */
public class DIHelper {

	public static final String SENDER = "gcmSenderId";
	private static AppComponent appComponent;

	public static void init(Context appContext) {
		appComponent = DaggerAppComponent.builder()
				.appModule(new AppModule(appContext))
				.restModule(new RestModule())
				.build();
	}

	public static AppComponent getAppComponent() {
		return appComponent;
	}
}
