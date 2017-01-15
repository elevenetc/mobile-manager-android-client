package su.elevenets.mobilemanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import su.elevenets.mobilemanager.di.DIHelper;
import su.elevenets.mobilemanager.managers.AppManager;
import su.elevenets.mobilemanager.managers.DeviceProfileManager;

import javax.inject.Inject;

/**
 * Created by eugene.levenetc on 25/11/2016.
 */
public class NetworkStateReceiver extends BroadcastReceiver {

	@Inject AppManager appManager;
	@Inject DeviceProfileManager deviceProfileManager;

	@Override public void onReceive(Context context, Intent intent) {
		DIHelper.getAppComponent().inject(this);

		if (appManager.isConnectedToNetwork())
			deviceProfileManager.updateOnlineState()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Action1<Object>() {
						@Override public void call(Object o) {

						}
					}, new Action1<Throwable>() {
						@Override public void call(Throwable throwable) {
							throwable.printStackTrace();
						}
					});
	}
}