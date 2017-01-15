package su.elevenets.mobilemanager;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import rx.Observable;
import rx.Single;
import su.elevenets.mobilemanager.consts.Key;
import su.elevenets.mobilemanager.managers.AppManager;
import su.elevenets.mobilemanager.managers.KeyValueManager;
import su.elevenets.mobilemanager.managers.RestManager;
import su.elevenets.mobilemanager.managers.loc.Loc;
import su.elevenets.mobilemanager.managers.loc.LocManager;
import su.elevenets.mobilemanager.models.DeviceProfile;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by eugene.levenetc on 18/12/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestSettingActivity {

	@Rule
	public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

	private RestManager restManager;
	private RestManager.Api api;
	private LocManager locManager;
	private KeyValueManager keyValueManager;
	private AppManager appManager;

	@Before public void before() {

		restManager = TestRunner.inst.restManager;
		api = TestRunner.inst.api;
		locManager = TestRunner.inst.locManager;
		keyValueManager = TestRunner.inst.keyValueManager;
		appManager = TestRunner.inst.appManager;

		when(locManager.getLocation()).thenReturn(Single.just(new Loc(0, 0)));
		when(api.updateLocation(anyString(), anyDouble(), anyDouble())).thenReturn(Single.just(new Object()));
		when(restManager.getApi()).thenReturn(api);
		when(keyValueManager.getBoolean(Key.BOUND)).thenReturn(false);
		when(keyValueManager.get(Key.END_POINT)).thenReturn("");
		when(appManager.isConnectedToNetwork()).thenReturn(true);
		when(appManager.getGcmToken()).thenReturn(Observable.just(""));
	}

	@Test public void sampleTest() {

		when(api.postDevice(any(DeviceProfile.class))).thenReturn(Observable.just(new Object()));

		onView(withId(R.id.edit_endpoint)).perform(typeText("ggg"));
		onView(withId(R.id.btn_bind)).perform(click());

		verify(api, times(1)).postDevice(any(DeviceProfile.class));
	}
}
