package su.elevenets.devicemanagerclient.presenters;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import su.elevenets.devicemanagerclient.consts.Key;
import su.elevenets.devicemanagerclient.managers.AppManager;
import su.elevenets.devicemanagerclient.managers.DeviceProfileManager;
import su.elevenets.devicemanagerclient.managers.KeyValueManager;
import su.elevenets.devicemanagerclient.managers.RestManager;
import su.elevenets.devicemanagerclient.utils.RxUtils;
import su.elevenets.devicemanagerclient.utils.Utils;
import su.elevenets.devicemanagerclient.views.SettingsView;

import javax.inject.Inject;

/**
 * Created by eleven on 21/08/2016.
 */
public class SettingsPresenter {

	@Inject AppManager appManager;
	@Inject RestManager restManager;
	@Inject KeyValueManager keyValueManager;
	@Inject DeviceProfileManager deviceProfileManager;

	private SettingsView view;
	private Subscription sub;

	public void onCreate() {

	}

	public void onViewCreated(SettingsView view) {
		this.view = view;
	}

	public void onViewDestroyed() {
		RxUtils.unsub(sub);
		this.view = null;
	}

	public void bind() {

		//TODO: add end point validation

		view.setProgress();
		String endpoint = view.getEndpoint();

		keyValueManager.store(KeyValueManager.END_POINT, endpoint);

		sub = deviceProfileManager.uploadDeviceProfile()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnNext(o -> keyValueManager.store(KeyValueManager.BOUND, true))
				.subscribe(o -> {
					keyValueManager.store(KeyValueManager.BOUND, true);
					view.setBindingSuccess();
				}, throwable -> view.setBindingError(throwable));
	}

	public void unbind() {
		view.setProgress();
		restManager.getApi(view.getEndpoint())
				.deleteDevice(appManager.getDeviceId())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(o -> {
					keyValueManager.store(KeyValueManager.BOUND, false);
					view.setUnbindingSuccess();
				}, throwable -> {
					throwable.printStackTrace();
					view.setBindingError(throwable);
				});
	}

	public String getEndpoint() {
		return keyValueManager.get(KeyValueManager.END_POINT);
	}

	public boolean isBound() {
		return keyValueManager.getBoolean(KeyValueManager.BOUND);
	}

	public boolean isLocationAllowed() {
		return appManager.isLocationAllowed();
	}

	public void enableLocation() {
		if (!appManager.isLocationAllowed()) {
			view.requestLocationPermission();
		} else {
			handleEnabledLocation();
		}
	}

	private void handleEnabledLocation() {
		keyValueManager.store(Key.LOC_ENABLED, true);
		view.locationEnabled();
	}

	private void handleDisabledLocation() {
		keyValueManager.store(Key.LOC_ENABLED, false);
		view.locationDisabled();
	}

	public void disableLocation() {
		keyValueManager.store(Key.LOC_ENABLED, false);
	}

	public boolean isLocationEnabled() {
		return keyValueManager.getBoolean(Key.LOC_ENABLED);
	}

	public void handlePermissionResult(int requestCode, int[] grantResults) {
		if (Utils.locationGranted(requestCode, grantResults)) handleEnabledLocation();
		else handleDisabledLocation();
	}
}