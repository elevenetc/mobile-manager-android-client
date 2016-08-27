package su.elevenets.devicemanagerclient.services;

import android.content.Intent;
import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Eugene Levenetc on 18/07/2016.
 */
public class InstanceIDService extends InstanceIDListenerService {

	@Override
	public void onTokenRefresh() {
		Intent intent = new Intent(this, GCMRegistrationService.class);
		startService(intent);
	}
}
