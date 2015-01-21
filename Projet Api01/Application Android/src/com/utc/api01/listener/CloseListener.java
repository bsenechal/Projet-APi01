package com.utc.api01.listener;

import android.app.Activity;
import android.content.DialogInterface;

/**
 * Close the application, usually used when an error occurs
 */
public class CloseListener implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener, Runnable {

	private final Activity activityToClose;

	public CloseListener(Activity activityToClose) {
		this.activityToClose = activityToClose;
	}

	public void onCancel(DialogInterface dialogInterface) {
		run();
	}

	public void onClick(DialogInterface dialogInterface, int i) {
		run();
	}

	public void run() {
		activityToClose.finish();
	}
}

