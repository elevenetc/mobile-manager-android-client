package su.elevenets.devicemanagerclient.managers;

import android.support.annotation.Nullable;

/**
 * Created by eleven on 28/08/2016.
 */
public interface KeyValueManager {

	String GCM_TOKEN = "pushToken";
	String BOUND = "bound";
	String SCREEN_WIDTH = "screenWidth";
	String SCREEN_HEIGHT = "screenHeight";
	String END_POINT = "lastEndPoint";

	void store(String key, String value);

	void store(String key, boolean value);

	void store(String key, int value);

	@Nullable String get(String key);

	int getInt(String key);

	boolean getBoolean(String key);
}
