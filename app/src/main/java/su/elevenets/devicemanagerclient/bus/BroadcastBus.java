package su.elevenets.devicemanagerclient.bus;

import rx.Observable;

/**
 * Created by eugene.levenetc on 14/11/2016.
 */
public interface BroadcastBus {

    Observable getObservable();

    void post(Object object);
}
