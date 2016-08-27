package su.elevenets.devicemanagerclient.views;

/**
 * Created by eleven on 21/08/2016.
 */
public interface SettingsView {
    String getEndpoint();

    void setBindingProgress();

    void setBindingError(Throwable throwable);

    void setBindingSuccess();
}
