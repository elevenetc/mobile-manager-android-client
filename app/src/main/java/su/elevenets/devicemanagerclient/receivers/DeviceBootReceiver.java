package su.elevenets.devicemanagerclient.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import su.elevenets.devicemanagerclient.di.DIHelper;
import su.elevenets.devicemanagerclient.managers.AppManager;
import su.elevenets.devicemanagerclient.managers.DeviceProfileManager;

import javax.inject.Inject;

/**
 * Created by eugene.levenetc on 26/11/2016.
 */
public class DeviceBootReceiver extends BroadcastReceiver {

	@Inject AppManager appManager;
	@Inject DeviceProfileManager deviceProfileManager;

	@Override public void onReceive(Context context, Intent intent) {
		DIHelper.getAppComponent().inject(this);
	}
}
