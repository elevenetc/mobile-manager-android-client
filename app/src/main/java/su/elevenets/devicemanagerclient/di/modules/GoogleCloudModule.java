package su.elevenets.devicemanagerclient.di.modules;

import dagger.Module;
import dagger.Provides;
import su.elevenets.devicemanagerclient.R;
import su.elevenets.devicemanagerclient.services.TokenSender;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by eleven on 27/08/2016.
 */
@Module
public class GoogleCloudModule {

	public static final String SENDER = "gcmSenderId";

	@Provides @Singleton public TokenSender provideTokenSender() {
		return new TokenSender() {
			@Override public void send(String token) {

			}
		};
	}

	@Provides @Singleton @Named(SENDER) public int provideSenderId() {
		return R.string.gcmSenderId;
	}
}
