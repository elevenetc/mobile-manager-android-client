package su.elevenets.devicemanagerclient.managers;

import android.content.Context;
import android.provider.Settings;
import com.google.firebase.iid.FirebaseInstanceId;
import rx.Observable;
import su.elevenets.devicemanagerclient.R;
import su.elevenets.devicemanagerclient.models.DeviceProfile;
import su.elevenets.devicemanagerclient.utils.RxUtils;

import static android.os.Build.MANUFACTURER;
import static android.os.Build.MODEL;

/**
 * Created by eleven on 28/08/2016.
 */
public class AppManagerImpl implements AppManager {

    private Context app;
    private KeyValueManager keyValueManager;

    public AppManagerImpl(Context app, KeyValueManager keyValueManager) {
        this.app = app;
        this.keyValueManager = keyValueManager;
    }



    @Override
    public int getSenderId() {
        return R.string.gcmSenderId;
    }

    @Override
    public String getManufacturer() {
        return MANUFACTURER;
    }

    @Override
    public String getModel() {
        return MODEL;
    }

    @Override
    public String getDeviceId() {
        return Settings.Secure.getString(app.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    @Override
    public Observable<String> getGcmToken() {

        return Observable.create(subscriber -> {

            try {
                final FirebaseInstanceId instance = FirebaseInstanceId.getInstance();
                final String token = instance.getToken();
                RxUtils.onNext(subscriber, token);
            } catch (Exception e) {
                RxUtils.onError(subscriber, e);
            }
        });
    }
}
