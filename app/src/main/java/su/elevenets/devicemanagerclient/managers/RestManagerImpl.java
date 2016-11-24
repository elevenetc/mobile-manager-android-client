package su.elevenets.devicemanagerclient.managers;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import su.elevenets.devicemanagerclient.consts.Key;

/**
 * Created by eleven on 28/08/2016.
 */
public class RestManagerImpl implements RestManager {

	private Api api;
	private String currentEndPoint;
	private KeyValueManager keyValueManager;

	public RestManagerImpl(KeyValueManager keyValueManager) {
		this.keyValueManager = keyValueManager;
	}

	@Override public Api getApi() {

		String endPoint = keyValueManager.get(Key.END_POINT);

		if (currentEndPoint == null || !currentEndPoint.equals(endPoint)) api = initApi(endPoint);
		currentEndPoint = endPoint;
		return api;
	}

	private Api initApi(String endPoint) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(endPoint)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();

		return retrofit.create(RestManager.Api.class);
	}
}
