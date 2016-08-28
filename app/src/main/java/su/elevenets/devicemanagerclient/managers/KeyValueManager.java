package su.elevenets.devicemanagerclient.managers;

import android.support.annotation.Nullable;

/**
 * Created by eleven on 28/08/2016.
 */
public interface KeyValueManager {

	String GCM_TOKEN = "gcmToken";
	String BOUND = "bound";
	String LAST_END_POINT = "lastEndPoint";

	void store(String key, String value);

	void storeBoolean(String key, boolean value);

	@Nullable String get(String key);

	@Nullable boolean getBoolean(String key);
}
