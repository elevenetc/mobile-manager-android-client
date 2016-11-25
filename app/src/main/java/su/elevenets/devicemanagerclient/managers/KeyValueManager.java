package su.elevenets.devicemanagerclient.managers;

import android.support.annotation.Nullable;

/**
 * Created by eleven on 28/08/2016.
 */
public interface KeyValueManager {

	void store(String key, double value);

	void store(String key, String value);

	void store(String key, boolean value);

	void store(String key, int value);

	@Nullable String get(String key);

	int getInt(String key);

	double getDouble(String key);

	boolean getBoolean(String key);

	void clear();
}
