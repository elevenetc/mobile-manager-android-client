package su.elevenets.devicemanagerclient.di.components;

import dagger.Component;
import su.elevenets.devicemanagerclient.di.modules.GoogleCloudModule;
import su.elevenets.devicemanagerclient.di.modules.RestModule;
import su.elevenets.devicemanagerclient.di.modules.ServicesModule;
import su.elevenets.devicemanagerclient.di.modules.SettingsModule;
import su.elevenets.devicemanagerclient.presenters.SettingsPresenter;
import su.elevenets.devicemanagerclient.services.GCMRegistrationService;
import su.elevenets.devicemanagerclient.services.GCMService;

import javax.inject.Singleton;

/**
 * Created by Eugene Levenetc on 23/07/2016.
 */
@Singleton
@Component(modules = {
		SettingsModule.class,
		ServicesModule.class,
		RestModule.class,
		GoogleCloudModule.class
})
public interface AppComponent {
	void inject(SettingsPresenter presenter);

	void inject(GCMService service);

	void inject(GCMRegistrationService registrationService);
}
