package su.elevenets.devicemanagerclient;

import android.app.Application;
import com.firebase.client.Firebase;
import su.elevenets.devicemanagerclient.di.DIHelper;

/**
 * Created by eleven on 27/08/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        DIHelper.init(this);
    }
}
