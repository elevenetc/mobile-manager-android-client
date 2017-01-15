package su.elevenets.mobilemanager.managers;

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

	@Override public void store(String key, double value) {
		sharedPreferences.edit().putLong(key, doubleToLong(value)).apply();
	}

	@Override public void store(String key, String value) {
		sharedPreferences.edit().putString(key, String.valueOf(value)).apply();
	}

	@Override public void store(String key, boolean value) {
		sharedPreferences.edit().putBoolean(key, value).apply();
	}

	@Override public void store(String key, int value) {
		sharedPreferences.edit().putInt(key, value).apply();
	}

	@Override @Nullable public String get(String key) {
		return sharedPreferences.getString(key, null);
	}

	@Override public int getInt(String key) {
		return sharedPreferences.getInt(key, 0);
	}

	@Override public double getDouble(String key) {
		return longToDouble(sharedPreferences.getLong(key, 0));
	}

	@Override @Nullable public boolean getBoolean(String key) {
		return sharedPreferences.getBoolean(key, false);
	}

	@Override public void clear() {
		sharedPreferences.edit().clear().apply();
	}

	private static double longToDouble(long value){
		return Double.longBitsToDouble(value);
	}

	private static long doubleToLong(double value) {
		return Double.doubleToRawLongBits(value);
	}
}
