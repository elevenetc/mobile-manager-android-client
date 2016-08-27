package su.elevenets.devicemanagerclient.services;

import android.os.Bundle;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Eugene Levenetc on 18/07/2016.
 */
public class GCMService extends GcmListenerService {
	@Override public void onMessageReceived(String s, Bundle bundle) {
		super.onMessageReceived(s, bundle);
	}
}
