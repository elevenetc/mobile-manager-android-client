package su.elevenets.devicemanagerclient.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Eugene Levenetc on 23/07/2016.
 */
public class SettingsManagerImpl implements SettingsManager {

	private final SharedPreferences prefs;
	private static final String GCM_TOKEN = "gcmToken";
	private static final String IS_GCM_REGISTERED = "isGCNRegistered";

	public SettingsManagerImpl(Context context) {
		prefs = context.getSharedPreferences("MainSettings", Context.MODE_PRIVATE);
	}

	@Override public String getGCMToken() {
		return prefs.getString(GCM_TOKEN, null);
	}

	@Override public void storeGCMToken(String token) {
		prefs.edit().putString(GCM_TOKEN, token).apply();
	}

	@Override public void setGCMTokenSent(boolean value) {
		prefs.edit().putBoolean(IS_GCM_REGISTERED, value).apply();
	}

	@Override public boolean isGCMTokenSent() {
		return prefs.getBoolean(IS_GCM_REGISTERED, false);
	}
}
