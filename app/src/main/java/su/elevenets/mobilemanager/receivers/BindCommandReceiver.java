package su.elevenets.mobilemanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import su.elevenets.mobilemanager.bus.BroadcastBus;
import su.elevenets.mobilemanager.bus.events.RemoteBindCommand;
import su.elevenets.mobilemanager.di.DIHelper;

import javax.inject.Inject;

/**
 * Created by eugene.levenetc on 22/12/2016.
 *
 * adb shell am broadcast -a su.elevenets.mobilemanager.intent.Bind --es command "bind"
 */
public class BindCommandReceiver extends BroadcastReceiver {

	@Inject BroadcastBus bus;

	@Override public void onReceive(Context context, Intent intent) {
		String command = intent.getStringExtra("command");

		if ("bind".equals(command)) {
			DIHelper.getAppComponent().inject(this);
			bus.post(new RemoteBindCommand());
		}
	}
}
