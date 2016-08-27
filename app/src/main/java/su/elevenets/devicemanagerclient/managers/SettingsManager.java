package su.elevenets.devicemanagerclient.managers;

/**
 * Created by Eugene Levenetc on 23/07/2016.
 */
public interface SettingsManager {

	String getGCMToken();

	void storeGCMToken(String token);

	void setGCMTokenSent(boolean value);

	boolean isGCMTokenSent();
}
