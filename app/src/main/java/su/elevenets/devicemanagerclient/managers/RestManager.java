package su.elevenets.devicemanagerclient.managers;

import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by eleven on 27/08/2016.
 */
public interface RestManager {
	@POST("gcmToken") Observable<Object> sendGCMToken(@Query("token") String token);

	@DELETE("gcmToken") Observable<Object> clearGCMToken(@Query("token") String token);
}
