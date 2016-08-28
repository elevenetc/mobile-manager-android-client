package su.elevenets.devicemanagerclient.services;

import rx.Completable;

/**
 * Created by eleven on 27/08/2016.
 */
public interface TokenSender {
	Completable send(String token);
}
