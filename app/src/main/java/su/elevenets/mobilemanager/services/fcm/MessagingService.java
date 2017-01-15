package su.elevenets.mobilemanager.services.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import su.elevenets.mobilemanager.bus.BroadcastBus;
import su.elevenets.mobilemanager.bus.events.PingEvent;
import su.elevenets.mobilemanager.di.DIHelper;
import su.elevenets.mobilemanager.managers.Logger;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by eugene.levenetc on 13/11/2016.
 */
public class MessagingService extends FirebaseMessagingService {

	private static final String TAG = MessagingService.class.getSimpleName();

	@Inject BroadcastBus broadcastBus;
	@Inject Logger logger;

	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {

		DIHelper.getAppComponent().inject(this);

		super.onMessageReceived(remoteMessage);
		final Map<String, String> data = remoteMessage.getData();
		final String from = remoteMessage.getFrom();
		final String messageId = remoteMessage.getMessageId();
		final String messageType = remoteMessage.getMessageType();

		logger.debug(TAG, "onMessageReceived");

		if (data.containsKey("command")) {
			if (data.containsValue("ping")) {
				logger.debug(TAG, "ping event sent");
				broadcastBus.post(new PingEvent());
			}
		}
	}
}
