package su.elevenets.devicemanagerclient.services.fcm;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by eugene.levenetc on 13/11/2016.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    //@Inject
    //AppManager appManager;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //TODO: update refreshed token
    }
}
