package su.elevenets.devicemanagerclient;

import android.app.Application;
import su.elevenets.devicemanagerclient.di.DIHelper;

/**
 * Created by eleven on 27/08/2016.
 */
public class App extends Application{
	@Override public void onCreate() {
		super.onCreate();
		DIHelper.init(this);
	}
}
