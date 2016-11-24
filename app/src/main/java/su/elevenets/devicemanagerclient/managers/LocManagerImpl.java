package su.elevenets.devicemanagerclient.managers;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import rx.Single;
import su.elevenets.devicemanagerclient.utils.Utils;

/**
 * Created by eugene.levenetc on 23/11/2016.
 */
public class LocManagerImpl implements LocManager, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

	private GoogleApiClient apiClient;
	private Context appContext;

	public LocManagerImpl(Context appContext) {
		this.appContext = appContext;
		init(appContext);
	}

	@Override public Single<Location> getLocation() {
		if (Utils.isLocationAllowed(appContext)) {
			return Single.create(singleSubscriber -> {
						final LocationRequest locationRequest = new LocationRequest();

				final Thread thread = Thread.currentThread();
				if(thread == null){

				}

				LocationServices.FusedLocationApi.requestLocationUpdates(
								apiClient,
								locationRequest, location -> {
									if (!singleSubscriber.isUnsubscribed()) singleSubscriber.onSuccess(location);
								}
						);
					}
			);
		} else {
			return Single.error(new IllegalStateException("Location is not allowed"));
		}
	}

	private void init(Context context) {
		apiClient = new GoogleApiClient.Builder(context)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API)
				.build();
		apiClient.connect();
	}

	@Override public void onConnected(@Nullable Bundle bundle) {

	}

	@Override public void onConnectionSuspended(int i) {

	}

	@Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

	}
}
