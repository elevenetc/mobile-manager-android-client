package su.elevenets.mobilemanager;

import android.app.Application;
import com.firebase.client.Firebase;
import su.elevenets.mobilemanager.di.DIHelper;
import su.elevenets.mobilemanager.managers.DeviceProfileManager;

import javax.inject.Inject;

/**
 * Created by eleven on 27/08/2016.
 */
public class App extends Application {

	@Inject DeviceProfileManager deviceProfileManager;

	@Override public void onCreate() {
		super.onCreate();
		Firebase.setAndroidContext(this);
		DIHelper.init(this);
		DIHelper.getAppComponent().inject(this);
	}
}