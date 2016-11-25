package su.elevenets.devicemanagerclient.managers;

import retrofit2.http.*;
import rx.Observable;
import rx.Single;
import su.elevenets.devicemanagerclient.models.DeviceProfile;

/**
 * Created by eleven on 27/08/2016.
 */
public interface RestManager {

	Api getApi();

	interface Api {
		@POST("devices")
		Observable<Object> postDevice(@Body DeviceProfile deviceProfile);

		@POST("pong/{deviceId}")
		Observable<Object> pong(@Path("deviceId") String deviceId);

		@DELETE("devices/{deviceId}")
		Observable<Object> deleteDevice(@Path("deviceId") String deviceId);

		@POST("location/{deviceId}")
		Single<Object> updateLocation(@Path("deviceId") String deviceId, @Query("lat") double lat, @Query("lon") double lon);

		@POST("online/{deviceId}")
		Single<Object> updateOnlineState(@Path("deviceId") String deviceId, @Query("isOnline") boolean isOnline);
	}
}
