package su.elevenets.devicemanagerclient.di.modules;

import dagger.Module;
import dagger.Provides;
import rx.Completable;
import su.elevenets.devicemanagerclient.R;
import su.elevenets.devicemanagerclient.di.DIHelper;
import su.elevenets.devicemanagerclient.managers.RestManager;
import su.elevenets.devicemanagerclient.models.Device;
import su.elevenets.devicemanagerclient.services.TokenSender;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by eleven on 27/08/2016.
 */
@Module
public class GoogleCloudModule {

	@Provides @Singleton public TokenSender provideTokenSender(RestManager restManager) {
		return new TokenSender() {
			@Override public Completable send(String token) {
				Device device = new Device();
//				device.
//				restManager.postDevice(device)
				return null;
			}
		};
	}

	@Provides @Singleton @Named(DIHelper.SENDER) public int provideSenderId() {
		return R.string.gcmSenderId;
	}
}
