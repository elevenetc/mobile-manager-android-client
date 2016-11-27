package su.elevenets.devicemanagerclient.services.fcm;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import su.elevenets.devicemanagerclient.bus.BroadcastBus;
import su.elevenets.devicemanagerclient.bus.events.PingEvent;
import su.elevenets.devicemanagerclient.consts.Tags;
import su.elevenets.devicemanagerclient.di.DIHelper;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by eugene.levenetc on 13/11/2016.
 */
public class MessagingService extends FirebaseMessagingService {

	@Inject BroadcastBus broadcastBus;

	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {

		DIHelper.getAppComponent().inject(this);

		super.onMessageReceived(remoteMessage);
		final Map<String, String> data = remoteMessage.getData();
		final String from = remoteMessage.getFrom();
		final String messageId = remoteMessage.getMessageId();
		final String messageType = remoteMessage.getMessageType();

		Log.d(Tags.APP, "onMessageReceived");

		if (data.containsKey("command")) {
			if (data.containsValue("ping")) {
				Log.d(Tags.APP, "ping event sent");
				broadcastBus.post(new PingEvent());
			}
		}
	}
}
