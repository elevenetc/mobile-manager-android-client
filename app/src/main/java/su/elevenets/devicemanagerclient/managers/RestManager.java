package su.elevenets.devicemanagerclient.managers;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
import su.elevenets.devicemanagerclient.models.DeviceProfile;

/**
 * Created by eleven on 27/08/2016.
 */
public interface RestManager {

    Api getApi(String endPoint);

    interface Api {
        @POST("devices")
        Observable<Object> postDevice(@Body DeviceProfile deviceProfile);

        @POST("pong/{deviceId}")
        Observable<Object> pong(@Path("deviceId") String deviceId);

        @DELETE("devices/{deviceId}")
        Observable<Object> deleteDevice(@Path("deviceId") String deviceId);
    }
}
