package su.elevenets.devicemanagerclient.services.fcm;

import com.google.firebase.iid.FirebaseInstanceIdService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import su.elevenets.devicemanagerclient.di.DIHelper;
import su.elevenets.devicemanagerclient.managers.DeviceProfileManager;

import javax.inject.Inject;

/**
 * Created by eugene.levenetc on 13/11/2016.
 */
public class InstanceIDService extends FirebaseInstanceIdService {

	@Inject DeviceProfileManager deviceProfileManager;

	@Override public void onTokenRefresh() {
		super.onTokenRefresh();
		DIHelper.getAppComponent().inject(this);

		deviceProfileManager.uploadDeviceProfile()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe();
	}
}