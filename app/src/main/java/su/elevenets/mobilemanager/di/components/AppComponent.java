package su.elevenets.mobilemanager.di.components;

import dagger.Component;
import su.elevenets.mobilemanager.App;
import su.elevenets.mobilemanager.MainActivity;
import su.elevenets.mobilemanager.di.modules.AppModule;
import su.elevenets.mobilemanager.di.modules.RestModule;
import su.elevenets.mobilemanager.fragments.SettingsFragment;
import su.elevenets.mobilemanager.presenters.SettingsPresenter;
import su.elevenets.mobilemanager.receivers.BindCommandReceiver;
import su.elevenets.mobilemanager.receivers.DeviceBootReceiver;
import su.elevenets.mobilemanager.receivers.NetworkStateReceiver;
import su.elevenets.mobilemanager.services.fcm.InstanceIDService;
import su.elevenets.mobilemanager.services.fcm.MessagingService;

import javax.inject.Singleton;

/**
 * Created by Eugene Levenetc on 23/07/2016.
 */
@Singleton
@Component(modules = {
		AppModule.class,
		RestModule.class,
})
public interface AppComponent {
	void inject(SettingsPresenter presenter);

	void inject(SettingsFragment settingsFragment);

	void inject(InstanceIDService instanceIDService);

	void inject(MessagingService messagingService);

	void inject(MainActivity mainActivity);

	void inject(NetworkStateReceiver networkStateReceiver);

	void inject(DeviceBootReceiver deviceBootReceiver);

	void inject(App app);

	void inject(BindCommandReceiver bindCommandReceiver);
}
