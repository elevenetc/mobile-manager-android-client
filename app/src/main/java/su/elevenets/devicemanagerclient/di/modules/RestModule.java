package su.elevenets.devicemanagerclient.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import su.elevenets.devicemanagerclient.managers.RestManager;

import javax.inject.Singleton;

/**
 * Created by Eugene Levenetc on 23/07/2016.
 */
@Module
public class RestModule {
	@Provides @Singleton public RestManager provideRestManager() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://192.168.96.254:8080/")
//				.baseUrl("http://52.174.151.207:8080/")
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();

		return retrofit.create(RestManager.class);
	}
}
