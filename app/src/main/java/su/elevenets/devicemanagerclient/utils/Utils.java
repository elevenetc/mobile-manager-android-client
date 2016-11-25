package su.elevenets.devicemanagerclient.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import su.elevenets.devicemanagerclient.consts.RequestCodes;

/**
 * Created by eugene.levenetc on 23/11/2016.
 */
public class Utils {

	public static double getScreenSize(Activity activity, int screenWidth, int screenHeight) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		double x = Math.pow(screenWidth / dm.xdpi, 2);
		double y = Math.pow(screenHeight / dm.ydpi, 2);
		return Math.sqrt(x + y);
	}

	public static boolean locationGranted(int requestCode, int[] grantResults) {
		boolean result = true;

		if (requestCode == RequestCodes.PERMISSION_LOCATION)
			for (int grantResult : grantResults) {
				if (grantResult == PackageManager.PERMISSION_DENIED) {
					result = false;
					break;
				}
			}

		return result;
	}

	public static boolean fingerPrintGranted(int requestCode, int[] grantResults) {
		boolean result = true;

		if (requestCode == RequestCodes.PERMISSION_LOCATION)
			for (int grantResult : grantResults) {
				if (grantResult == PackageManager.PERMISSION_DENIED) {
					result = false;
					break;
				}
			}

		return result;
	}


	public static void requestLocationPermission(Fragment fragment) {
		fragment.requestPermissions(
				new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
				RequestCodes.PERMISSION_LOCATION
		);
	}

	public static void requestLocationPermission(Activity activity) {
		ActivityCompat.requestPermissions(
				activity,
				new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
				RequestCodes.PERMISSION_LOCATION
		);
	}

	public static boolean isLocationAllowed(Context context) {
		final boolean fineLocationGranted = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
		final boolean coarseLocationGranted = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
		return fineLocationGranted && coarseLocationGranted;
	}

	public static boolean isFingerPrintSensorAllowed(Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			final boolean granted = ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED;
			if (granted) {

			}
			return granted;
		} else {
			return false;
		}
	}

	public static void requestFingerPrintPermission(Fragment fragment) {
		fragment.requestPermissions(
				new String[]{Manifest.permission.USE_FINGERPRINT},
				RequestCodes.PERMISSION_LOCATION
		);
	}
}