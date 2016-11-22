package su.elevenets.devicemanagerclient.di.components;

import dagger.Component;
import su.elevenets.devicemanagerclient.MainActivity;
import su.elevenets.devicemanagerclient.di.modules.AppModule;
import su.elevenets.devicemanagerclient.di.modules.GoogleCloudModule;
import su.elevenets.devicemanagerclient.di.modules.RestModule;
import su.elevenets.devicemanagerclient.fragments.SettingsFragment;
import su.elevenets.devicemanagerclient.presenters.SettingsPresenter;
import su.elevenets.devicemanagerclient.services.fcm.InstanceIDService;
import su.elevenets.devicemanagerclient.services.fcm.MessagingService;

import javax.inject.Singleton;

/**
 * Created by Eugene Levenetc on 23/07/2016.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        RestModule.class,
        GoogleCloudModule.class
})
public interface AppComponent {
    void inject(SettingsPresenter presenter);

    void inject(SettingsFragment settingsFragment);

    void inject(InstanceIDService instanceIDService);

    void inject(MessagingService messagingService);

    void inject(MainActivity mainActivity);
}
