package su.elevenets.devicemanagerclient.managers;

import rx.Observable;
import rx.functions.Func1;
import su.elevenets.devicemanagerclient.models.DeviceProfile;


/**
 * Created by eugene.levenetc on 14/11/2016.
 */
public class DeviceProfileManagerImpl implements DeviceProfileManager {

    private RestManager restManager;
    private KeyValueManager keyValueManager;
    private AppManager appManager;

    public DeviceProfileManagerImpl(RestManager restManager, KeyValueManager keyValueManager, AppManager appManager) {
        this.restManager = restManager;
        this.keyValueManager = keyValueManager;
        this.appManager = appManager;
    }

    @Override
    public Observable<Object> uploadDeviceProfile() {
        return getDeviceProfile().flatMap(new Func1<DeviceProfile, Observable<Object>>() {
            @Override
            public Observable<Object> call(DeviceProfile deviceProfile) {
                final String endPoint = keyValueManager.get(KeyValueManager.END_POINT);
                return restManager.getApi(endPoint).postDevice(deviceProfile);
            }
        });
    }

    @Override
    public Observable<DeviceProfile> getDeviceProfile() {
        return appManager.getGcmToken().map(token -> {
            DeviceProfile deviceProfile = new DeviceProfile();
            deviceProfile.gcmToken = token;
            deviceProfile.deviceId = appManager.getDeviceId();
            deviceProfile.manufacturer = appManager.getManufacturer();
            deviceProfile.model = appManager.getModel();
            return deviceProfile;
        });
    }
}
