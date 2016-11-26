package su.elevenets.devicemanagerclient.services.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import su.elevenets.devicemanagerclient.bus.BroadcastBus;
import su.elevenets.devicemanagerclient.bus.events.PingEvent;
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

		if (data.containsKey("command")) {
			if (data.containsValue("ping")) {
				broadcastBus.post(new PingEvent());
			}
		}
	}
}
