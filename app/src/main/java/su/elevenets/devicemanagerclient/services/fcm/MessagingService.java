package su.elevenets.devicemanagerclient.services.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import su.elevenets.devicemanagerclient.consts.Key;
import su.elevenets.devicemanagerclient.di.DIHelper;
import su.elevenets.devicemanagerclient.managers.AppManager;
import su.elevenets.devicemanagerclient.managers.KeyValueManager;
import su.elevenets.devicemanagerclient.managers.RestManager;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by eugene.levenetc on 13/11/2016.
 */
public class MessagingService extends FirebaseMessagingService {

	@Inject RestManager restManager;
	@Inject KeyValueManager keyValueManager;
	@Inject AppManager appManager;

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
				restManager
						.getApi()
						.pong(appManager.getDeviceId())
						.subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(new Action1<Object>() {
							@Override
							public void call(Object o) {
								if (o == null) {

								}
							}
						}, new Action1<Throwable>() {
							@Override
							public void call(Throwable throwable) {
								throwable.printStackTrace();
							}
						});
			}
		}
	}
}
