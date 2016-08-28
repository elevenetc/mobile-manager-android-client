package su.elevenets.devicemanagerclient.services;

import com.google.android.gms.iid.InstanceIDListenerService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import su.elevenets.devicemanagerclient.di.DIHelper;
import su.elevenets.devicemanagerclient.managers.AppManager;

import javax.inject.Inject;

/**
 * Created by Eugene Levenetc on 18/07/2016.
 */
public class InstanceIDService extends InstanceIDListenerService {

	@Inject AppManager appManager;

	@Override
	public void onTokenRefresh() {
		DIHelper.getAppComponent().inject(this);

		appManager.getGcmToken()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe();
	}
}
