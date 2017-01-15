package su.elevenets.mobilemanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import su.elevenets.mobilemanager.di.DIHelper;
import su.elevenets.mobilemanager.managers.AppManager;
import su.elevenets.mobilemanager.managers.DeviceProfileManager;

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
