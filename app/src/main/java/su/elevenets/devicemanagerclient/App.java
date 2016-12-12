package su.elevenets.devicemanagerclient;

import android.app.Application;
import android.util.Log;
import com.firebase.client.Firebase;
import su.elevenets.devicemanagerclient.consts.Tags;
import su.elevenets.devicemanagerclient.di.DIHelper;
import su.elevenets.devicemanagerclient.managers.DeviceProfileManager;

import javax.inject.Inject;

/**
 * Created by eleven on 27/08/2016.
 */
public class App extends Application {

	@Inject DeviceProfileManager deviceProfileManager;

	@Override public void onCreate() {
		super.onCreate();
		Log.d(Tags.APP, "onCreate");
		Firebase.setAndroidContext(this);
		DIHelper.init(this);
		DIHelper.getAppComponent().inject(this);

		//TODO: replace Log.xxx
	}
}