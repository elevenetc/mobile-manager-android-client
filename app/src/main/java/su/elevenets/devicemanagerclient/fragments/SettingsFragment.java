package su.elevenets.devicemanagerclient.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import su.elevenets.devicemanagerclient.R;
import su.elevenets.devicemanagerclient.di.DIHelper;
import su.elevenets.devicemanagerclient.presenters.SettingsPresenter;
import su.elevenets.devicemanagerclient.utils.Utils;
import su.elevenets.devicemanagerclient.views.SettingsView;

/**
 * Created by eleven on 21/08/2016.
 */
public class SettingsFragment extends Fragment implements SettingsView {

	public static Fragment create() {
		return new SettingsFragment();
	}

	@BindView(R.id.edit_endpoint) TextView editEndpoint;
	@BindView(R.id.btn_bind) Button btnBind;
	@BindView(R.id.btn_unbind) Button btnUnbind;
	@BindView(R.id.check_location_enabled) CheckBox locationCheck;

	private Unbinder binder;
	private SettingsPresenter presenter;

	public SettingsFragment() {
		setRetainInstance(true);
	}

	@Override public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		presenter = new SettingsPresenter();
		DIHelper.getAppComponent().inject(this);
		DIHelper.getAppComponent().inject(presenter);
	}

	@Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View result = inflater.inflate(R.layout.fragment_settings, container, false);
		binder = ButterKnife.bind(this, result);
		updateUi();
		return result;
	}

	@Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		presenter.onViewCreated(this);
	}

	@Override public void onDestroyView() {
		presenter.onViewDestroyed();
		super.onDestroyView();
	}

	@Override public String getEndpoint() {
		return editEndpoint.getText().toString();
	}

	@Override public void setProgress() {
		disableView();
	}

	@Override public void setBindingError(Throwable throwable) {
		Toast.makeText(getContext(), R.string.error_binding, Toast.LENGTH_SHORT).show();
		updateUi();
	}

	@Override public void setBindingSuccess() {
		updateUi();
	}

	@Override public void setUnbindingSuccess() {
		updateUi();
	}

	@OnClick(R.id.btn_bind) void bindDevice() {
		presenter.bind();
	}

	@OnClick(R.id.btn_unbind) void unbindDevice() {
		presenter.unbind();
	}

	@OnClick(R.id.btn_clear) void clearSettings() {
		presenter.resetSettings();
		updateUi();
	}

	@Override public void requestLocationPermission() {
		Utils.requestLocationPermission(this);
	}

	@Override public void locationEnabled() {
		updateLocation(true);
	}

	@Override public void locationDisabled() {
		updateLocation(false);
	}

	@Override public void requestFingerPrintPermission() {
		Utils.requestFingerPrintPermission(this);
	}

	@Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		presenter.handlePermissionResult(requestCode, grantResults);
	}

	@OnClick(R.id.check_location_enabled) void handleLocationCheck() {
		final boolean checked = locationCheck.isChecked();
		if (checked) presenter.enableLocation();
		else presenter.disableLocation();
	}

	private void updateUi() {

		final String endPoint = presenter.getEndpoint();
		final boolean isBound = presenter.isBound();

		if (endPoint != null) editEndpoint.setText(endPoint);

		if (isBound) {
			editEndpoint.setEnabled(false);
			btnBind.setEnabled(false);
			btnUnbind.setEnabled(true);
		} else {
			editEndpoint.setEnabled(true);
			btnBind.setEnabled(true);
			btnUnbind.setEnabled(false);
		}

		updateLocation(presenter.isLocationEnabled());
	}

	private void updateLocation(boolean isEnabled) {
		locationCheck.setChecked(isEnabled);
	}

	private void disableView() {
		btnBind.setEnabled(false);
		btnUnbind.setEnabled(false);
		editEndpoint.setEnabled(false);
	}
}
