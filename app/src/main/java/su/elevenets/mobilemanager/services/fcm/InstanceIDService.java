package su.elevenets.mobilemanager.services.fcm;

import com.google.firebase.iid.FirebaseInstanceIdService;
import su.elevenets.mobilemanager.bus.BroadcastBus;
import su.elevenets.mobilemanager.bus.events.FirebaseTokenRefreshedEvent;
import su.elevenets.mobilemanager.di.DIHelper;

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