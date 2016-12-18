package su.elevenets.devicemanagerclient.managers;

import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by eugene.levenetc on 18/12/2016.
 */
public class LoggerImpl implements Logger{

	private final String tag;

	public LoggerImpl(String tag) {
		this.tag = tag;
	}

	@Override public void debug(String tag, String message) {
		Log.d(tag, message);
	}

	@Override public void error(String tag, String message) {
		Log.e(tag, message);
	}

	@Override public void error(@Nullable String tag, Throwable message) {
		Log.e(tag == null ? this.tag : tag, message.getMessage());
		message.printStackTrace();
	}

	@Override public void error(String message) {
		error(null, message);
	}

	@Override public void error(Throwable message) {
		error(null, message);
	}
}
