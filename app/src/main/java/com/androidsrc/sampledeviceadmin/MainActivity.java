package com.androidsrc.sampledeviceadmin;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

	private PolicyManager policyManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		policyManager = new PolicyManager(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.activate_admin:
			if (!policyManager.isAdminActive()) {
				Intent activateDeviceAdmin = new Intent(
						DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
				activateDeviceAdmin.putExtra(
						DevicePolicyManager.EXTRA_DEVICE_ADMIN,
						policyManager.getAdminComponent());
				activateDeviceAdmin
						.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
								"After activating admin, you will be able to block application uninstallation.");
				startActivityForResult(activateDeviceAdmin,
						PolicyManager.DPM_ACTIVATION_REQUEST_CODE);
			}
			break;
		case R.id.deactivate_admin:
			if (policyManager.isAdminActive())
				policyManager.disableAdmin();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == Activity.RESULT_OK
				&& requestCode == PolicyManager.DPM_ACTIVATION_REQUEST_CODE) {
			// handle code for successfull enable of admin
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}

	}
}
