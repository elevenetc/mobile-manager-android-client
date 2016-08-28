package su.elevenets.devicemanagerclient.presenters;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import su.elevenets.devicemanagerclient.managers.AppManager;
import su.elevenets.devicemanagerclient.managers.KeyValueManager;
import su.elevenets.devicemanagerclient.managers.RestManager;
import su.elevenets.devicemanagerclient.models.Device;
import su.elevenets.devicemanagerclient.utils.RxUtils;
import su.elevenets.devicemanagerclient.views.SettingsView;

import javax.inject.Inject;

/**
 * Created by eleven on 21/08/2016.
 */
public class SettingsPresenter {

	public @Inject AppManager appManager;
	public @Inject RestManager restManager;
	public @Inject KeyValueManager keyValueManager;

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

		view.setBindingProgress();
		String endpoint = view.getEndpoint();
		sub = appManager.getGcmToken()
				.flatMap(new Func1<String, Observable<Object>>() {
					@Override public Observable<Object> call(String token) {
						return restManager.getApi(endpoint).postDevice(createDevice(token));
					}
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnNext(o -> keyValueManager.store(KeyValueManager.BOUND, true))
				.subscribe(o -> {
					keyValueManager.store(KeyValueManager.BOUND, true);
					view.setBindingSuccess();
				}, throwable -> view.setBindingError(throwable));
	}

	private Device createDevice(String token) {
		Device device = new Device();
		device.gcmToken = token;
		device.deviceId = appManager.getDeviceId();
		device.manufacturer = appManager.getManufacturer();
		device.model = appManager.getModel();
		return device;
	}
}