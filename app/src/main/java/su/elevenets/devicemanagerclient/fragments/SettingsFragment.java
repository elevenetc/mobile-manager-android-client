package su.elevenets.devicemanagerclient.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import su.elevenets.devicemanagerclient.R;
import su.elevenets.devicemanagerclient.presenters.SettingsPresenter;
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
	private Unbinder binder;
	private SettingsPresenter presenter;

	public SettingsFragment() {
		setRetainInstance(true);
	}

	@Override public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		presenter = new SettingsPresenter();
	}

	@Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View result = inflater.inflate(R.layout.fragment_settings, container, false);
		binder = ButterKnife.bind(this, result);
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

	@Override public void setBindingProgress() {
		editEndpoint.setEnabled(false);
		btnBind.setEnabled(false);
		btnBind.setText(R.string.binding);
	}

	@Override public void setBindingError(Throwable throwable) {
		Toast.makeText(getContext(), R.string.error_binding, Toast.LENGTH_SHORT).show();
		editEndpoint.setEnabled(true);
		btnBind.setEnabled(true);
		btnBind.setText(R.string.bind);
	}

	@Override public void setBindingSuccess() {
		btnBind.setText(R.string.unbind);
		btnBind.setEnabled(true);
	}

	@OnClick(R.id.btn_bind) public void bindDevice() {
		presenter.bind();
	}
}
