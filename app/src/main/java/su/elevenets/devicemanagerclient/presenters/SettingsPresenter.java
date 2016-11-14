package su.elevenets.devicemanagerclient.presenters;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import su.elevenets.devicemanagerclient.managers.AppManager;
import su.elevenets.devicemanagerclient.managers.DeviceProfileManager;
import su.elevenets.devicemanagerclient.managers.KeyValueManager;
import su.elevenets.devicemanagerclient.managers.RestManager;
import su.elevenets.devicemanagerclient.utils.RxUtils;
import su.elevenets.devicemanagerclient.views.SettingsView;

import javax.inject.Inject;

/**
 * Created by eleven on 21/08/2016.
 */
public class SettingsPresenter {

    public
    @Inject
    AppManager appManager;
    public
    @Inject
    RestManager restManager;
    public
    @Inject
    KeyValueManager keyValueManager;

    @Inject
    DeviceProfileManager deviceProfileManager;

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
                .doOnNext(o -> keyValueManager.storeBoolean(KeyValueManager.BOUND, true))
                .subscribe(o -> {
                    keyValueManager.storeBoolean(KeyValueManager.BOUND, true);
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
                    keyValueManager.storeBoolean(KeyValueManager.BOUND, false);
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
}