package su.elevenets.devicemanagerclient.managers;

import android.content.Context;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import rx.Completable;
import rx.Observable;
import rx.Subscriber;

import java.io.IOException;

/**
 * Created by Eugene Levenetc on 23/07/2016.
 */
public class ServicesManagerImpl implements ServicesManager {

	private Context context;
	private SettingsManager settingsManager;
	private RestManager restManager;
	private int gcmSenderId;

	public ServicesManagerImpl(
			Context context,
			SettingsManager settingsManager,
			RestManager restManager,
			int gcmSenderId
	) {
		this.context = context;
		this.settingsManager = settingsManager;
		this.restManager = restManager;
		this.gcmSenderId = gcmSenderId;
	}

	@Override public Completable sendGCMToken() {
		return getToken().flatMap(this::sendToken).toCompletable();
	}

	@Override public Completable clearGCMToken() {
		return getToken().flatMap(s -> restManager.clearGCMToken(s)).toCompletable();
	}

	private Observable<String> getToken() {
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override public void call(Subscriber<? super String> subscriber) {
				String token = settingsManager.getGCMToken();
				if (token == null) {
					try {
						subscriber.onNext(
								InstanceID.getInstance(context)
										.getToken(context.getString(gcmSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null)
						);
						subscriber.onCompleted();
					} catch (IOException e) {
						subscriber.onError(e);
					}
				}
			}
		});
	}

	private Observable<String> sendToken(String token) {
		return restManager.sendGCMToken(token).map(o -> token);
	}

	private Observable<Object> clearToken(String token) {
		return restManager.clearGCMToken(token);
	}
}
