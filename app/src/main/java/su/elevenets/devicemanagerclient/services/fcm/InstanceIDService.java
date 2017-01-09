package su.elevenets.devicemanagerclient.services.fcm;

import com.google.firebase.iid.FirebaseInstanceIdService;
import su.elevenets.devicemanagerclient.bus.BroadcastBus;
import su.elevenets.devicemanagerclient.bus.events.FirebaseTokenRefreshedEvent;
import su.elevenets.devicemanagerclient.di.DIHelper;

import javax.inject.Inject;

/**
 * Created by eugene.levenetc on 13/11/2016.
 */
public class InstanceIDService extends FirebaseInstanceIdService {

	@Inject BroadcastBus bus;

	@Override public void onTokenRefresh() {
		super.onTokenRefresh();
		DIHelper.getAppComponent().inject(this);
		bus.post(new FirebaseTokenRefreshedEvent());
	}
}