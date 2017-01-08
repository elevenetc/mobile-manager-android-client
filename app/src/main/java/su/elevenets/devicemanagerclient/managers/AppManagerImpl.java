package su.elevenets.devicemanagerclient.managers;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import com.google.firebase.iid.FirebaseInstanceId;
import rx.Observable;
import su.elevenets.devicemanagerclient.R;
import su.elevenets.devicemanagerclient.consts.Key;
import su.elevenets.devicemanagerclient.utils.RxUtils;
import su.elevenets.devicemanagerclient.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.os.Build.MANUFACTURER;
import static android.os.Build.MODEL;

/**
 * Created by eleven on 28/08/2016.
 */
public class AppManagerImpl implements AppManager {

	private Context app;
	private KeyValueManager keyValueManager;
	private Logger logger;

	public AppManagerImpl(
			Context app,
			KeyValueManager keyValueManager,
			Logger logger
	) {
		this.app = app;
		this.keyValueManager = keyValueManager;
		this.logger = logger;
	}

	@SuppressWarnings("unchecked")
	@Nullable @Override public String getSystemProp(String name) {

		try {
			Class clazz = Class.forName("android.os.SystemProperties");
			Method method = clazz.getDeclaredMethod("get", String.class);
			return (String) method.invoke(null, name);
		} catch (IllegalAccessException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException e) {
			logger.error(e);
			return null;
		}
	}

	@Override public void initValues(Activity activity) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		keyValueManager.store(Key.SCREEN_WIDTH, displaymetrics.widthPixels);
		keyValueManager.store(Key.SCREEN_HEIGHT, displaymetrics.heightPixels);
		keyValueManager.store(Key.SCREEN_SIZE, Utils.getScreenSize(activity, displaymetrics.widthPixels, displaymetrics.heightPixels));
	}

	@Override public boolean isConnectedToNetwork() {
		final ConnectivityManager conMgr = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
		return activeNetwork != null && activeNetwork.isConnected();
	}

	@Override public float batteryLevel() {
		final Intent batteryIntent = app.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		final int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		final int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

		if (level == -1 || scale == -1) return 50.0f;

		return ((float) level / (float) scale) * 100.0f;
	}

	@Override public int getScreenWidth() {
		return keyValueManager.getInt(Key.SCREEN_WIDTH);
	}

	@Override public int getScreenHeight() {
		return keyValueManager.getInt(Key.SCREEN_HEIGHT);
	}

	@Override public double getScreenSize() {
		return keyValueManager.getDouble(Key.SCREEN_SIZE);
	}

	@Override public String osVersion() {
		return Build.VERSION.RELEASE;
	}

	@Override public String getWiFiSSID() {
		WifiManager wifiManager = (WifiManager) app.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifiManager.getConnectionInfo();
		String result = info.getSSID();
		if (result.startsWith("\"") && result.endsWith("\""))
			result = result.substring(1, result.length() - 1);
		return result;
	}

	@Override public boolean hasNfc() {
		return app.getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC);
	}

	@Override @TargetApi(Build.VERSION_CODES.M)
	public boolean hasFingerprintScanner() {

		if (isFingerPrintAccessAllowed()) {
			FingerprintManager fingerprintManager = (FingerprintManager) app.getSystemService(Context.FINGERPRINT_SERVICE);
			return fingerprintManager.isHardwareDetected();
		} else {
			return false;
		}
	}

	@Override public boolean hasBluetooth() {
		return app.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH);
	}

	@Override public boolean hasBluetoothLowEnergy() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
			return app.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
		else
			return false;
	}

	@Override public int getSenderId() {
		return R.string.gcmSenderId;
	}

	@Override public String getManufacturer() {
		return MANUFACTURER;
	}

	@Override public String getModel() {
		return MODEL;
	}

	@Override public String getDeviceId() {
		return Settings.Secure.getString(app.getContentResolver(), Settings.Secure.ANDROID_ID);
	}

	@Override public Observable<String> getGcmToken() {

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

	@Override public boolean isLocationAllowed() {
		return Utils.isLocationAllowed(app);
	}

	@Override public boolean isAndroidM() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
	}

	@Override public boolean isFingerPrintAccessAllowed() {
		return Utils.isFingerPrintSensorAllowed(app);
	}

	@Override public String getCPUArch() {
		return System.getProperty("os.arch");
	}

	@Override public int getCPUCoreNum() {
		return Runtime.getRuntime().availableProcessors();
	}
}
