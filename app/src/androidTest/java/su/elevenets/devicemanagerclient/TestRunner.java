package su.elevenets.devicemanagerclient;

import android.app.Application;
import android.support.test.runner.AndroidJUnitRunner;
import su.elevenets.devicemanagerclient.di.DIHelper;
import su.elevenets.devicemanagerclient.managers.AppManager;
import su.elevenets.devicemanagerclient.managers.KeyValueManager;
import su.elevenets.devicemanagerclient.managers.RestManager;
import su.elevenets.devicemanagerclient.managers.loc.LocManager;

import static org.mockito.Mockito.mock;

/**
 * Created by eugene.levenetc on 18/12/2016.
 */
public class TestRunner extends AndroidJUnitRunner {

	public RestManager restManager;
	public RestManager.Api api;
	public LocManager locManager;
	public KeyValueManager keyValueManager;
	public AppManager appManager;

	public static TestRunner inst;

	@Override public void callApplicationOnCreate(Application app) {

		inst = this;

		restManager = mock(RestManager.class);
		api = mock(RestManager.Api.class);
		locManager = mock(LocManager.class);
		keyValueManager = mock(KeyValueManager.class);
		appManager = mock(AppManager.class);

		DIHelper.setRestManager(restManager);
		DIHelper.setLocManager(locManager);
		DIHelper.setKeyValueManager(keyValueManager);
		DIHelper.setAppManager(appManager);

		super.callApplicationOnCreate(app);
	}
}