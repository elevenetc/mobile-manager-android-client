package su.elevenets.devicemanagerclient.presenters;

import su.elevenets.devicemanagerclient.views.SettingsView;

/**
 * Created by eleven on 21/08/2016.
 */
public class SettingsPresenter {

	private SettingsView view;

	public void onViewCreated(SettingsView view) {
		this.view = view;
	}

	public void onViewDestroyed() {
		this.view = null;
	}

	public void bind() {
		view.setBindingProgress();
	}
}