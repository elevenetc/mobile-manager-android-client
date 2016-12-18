package su.elevenets.devicemanagerclient.managers;

/**
 * Created by eugene.levenetc on 18/12/2016.
 */
public interface Logger {
	void debug(String tag, String message);

	void error(String tag, String message);
	void error(String tag, Throwable message);
	void error(String message);
	void error(Throwable message);
}
