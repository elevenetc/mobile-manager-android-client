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

    public Single<Location> requestLocation() {
        if (Utils.isLocationAllowed(appContext)) {
            return Single.create(singleSubscriber -> LocationServices.FusedLocationApi.requestLocationUpdates(
                    apiClient,
                    new LocationRequest(), location -> {
                        if (!singleSubscriber.isUnsubscribed()) singleSubscriber.onSuccess(location);
                    })
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

        if (!Utils.isLocationAllowed(context)) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
