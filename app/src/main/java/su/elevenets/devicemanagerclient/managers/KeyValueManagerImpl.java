package su.elevenets.devicemanagerclient.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

/**
 * Created by eleven on 28/08/2016.
 */
public class KeyValueManagerImpl implements KeyValueManager {

	private final SharedPreferences sharedPreferences;

	public KeyValueManagerImpl(Context appContext) {
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
	}

	@Override public void store(String key, String value) {
		sharedPreferences.edit().putString(key, String.valueOf(value)).apply();
	}

	@Override public void store(String key, boolean value) {
		sharedPreferences.edit().putBoolean(key, value).apply();
	}

	@Override @Nullable public String get(String key) {
		return sharedPreferences.getString(key, null);
	}

	@Override @Nullable public boolean getBoolean(String key) {
		return sharedPreferences.getBoolean(key, false);
	}
}
