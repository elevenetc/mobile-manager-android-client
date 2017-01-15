package su.elevenets.mobilemanager.views;

/**
 * Created by eleven on 21/08/2016.
 */
public interface SettingsView {
    String getEndpoint();

    void setProgress();

    void setBindingError(Throwable throwable);

    void setBindingSuccess();

    void setUnbindingSuccess();

	void requestLocationPermission();

	void locationEnabled();

	void locationDisabled();

	void requestFingerPrintPermission();
}
