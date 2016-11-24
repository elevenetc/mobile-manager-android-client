package su.elevenets.devicemanagerclient.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import su.elevenets.devicemanagerclient.consts.RequestCodes;

/**
 * Created by eugene.levenetc on 23/11/2016.
 */
public class Utils {

	public static boolean locationGranted(int requestCode, int[] grantResults) {
		boolean result = true;

		if (requestCode == RequestCodes.PERMISSION_LOCATION_REQUEST)
			for (int grantResult : grantResults) {
				if (grantResult == PackageManager.PERMISSION_DENIED) {
					result = false;
					break;
				}
			}

		return result;
	}

	public static void requestLocationPermission(Fragment activity) {
		activity.requestPermissions(
				new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
				RequestCodes.PERMISSION_LOCATION_REQUEST
		);
	}

	public static void requestLocationPermission(Activity activity) {
		ActivityCompat.requestPermissions(
				activity,
				new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
				RequestCodes.PERMISSION_LOCATION_REQUEST
		);
	}

	public static boolean isLocationAllowed(Context context) {
		final boolean fineLocationGranted = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
		final boolean coarseLocationGranted = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
		return fineLocationGranted && coarseLocationGranted;
	}
}