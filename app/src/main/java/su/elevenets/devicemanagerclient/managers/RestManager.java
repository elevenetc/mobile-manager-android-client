package su.elevenets.devicemanagerclient.managers;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import su.elevenets.devicemanagerclient.models.Device;

/**
 * Created by eleven on 27/08/2016.
 */
public interface RestManager {

	Api getApi(String endPoint);

	interface Api {
		@POST("devices") Observable<Object> postDevice(@Body Device device);

		@DELETE("devices") Observable<Object> deleteDevice(@Query("deviceId") String deviceId);
	}
}
