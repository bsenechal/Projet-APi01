package com.utc.api01.activity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Size;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import cc.tamerelachauve.R;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.utc.api01.camera.BoxView;
import com.utc.api01.camera.Preview;

/**
 * Camera preview activity
 */
public class CameraPreview extends Activity implements SensorEventListener {

	private Preview mPreview;
	private BoxView mView;
	
	private TessBaseAPI baseApi;

	private SensorManager mSensorManager;
	private Sensor mAccel;
	private boolean mInitialized = false;
	private float mLastX = 0;
	private float mLastY = 0;
	private float mLastZ = 0;

	private int mScreenHeight;
	private int mScreenWidth;

	private boolean mInvalidate = false;

	/**
	 * This method is called when the camera preview activity is created
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(this.getClass().getSimpleName(), "onCreate()");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.preview);
		
		// the accelerometer is used for autofocus
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccel = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		// get the window width and height to display buttons
		// according to device screen size
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		mScreenHeight = displaymetrics.heightPixels;
		mScreenWidth = displaymetrics.widthPixels;
		
		// get the view containing camera preview
		mPreview = (Preview) findViewById(R.id.preview);
		
		// get the view containing the red box
		mView = (BoxView) findViewById(R.id.preview_boxView);
		mPreview.setBoxView(mView);

		//Init tesseract API
		baseApi = new TessBaseAPI();
		baseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST, "1234567890");
		baseApi.setDebug(true);
		baseApi.init(getExternalFilesDir(Environment.MEDIA_MOUNTED).getAbsolutePath()+"/", "fra");
		Log.i(this.getClass().getSimpleName(), "Init tesseract for language " + baseApi.getInitLanguagesAsString());
		mPreview.setBaseApi(baseApi);
		mPreview.setPath(getExternalFilesDir(Environment.MEDIA_MOUNTED).getAbsolutePath()+"/"+System.currentTimeMillis());
	}

	private AutoFocusCallback myAutoFocusCallback = new AutoFocusCallback() {

		public void onAutoFocus(boolean autoFocusSuccess, Camera arg1) {
			// Wait.oneSec();
		}
	};

	/**
	 * get the ratio between screen size and pixels of the image
	 * @return double[height,width]
	 */
	public Double[] getRatio() {
		Size s = mPreview.getCameraParameters().getPreviewSize();
		Log.d(this.getClass().getSimpleName(), "RATIO mPreviewH = " + s.height + " mPreviewW = "
				+ s.width + " mScreenH = " + mScreenHeight + " mScreenW = "
				+ mScreenWidth + " mViewH = " + mView.getParentHeight()
				+ " mViewW = " + mView.getParentWidth());
		double heightRatio = (double) s.height
				/ (double) mView.getParentHeight();
		double widthRatio = (double) s.width / (double) mView.getParentWidth();
		Double[] ratio = { heightRatio, widthRatio };
		return ratio;
	}

	/**
	 * close the app and release resources
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * Mainly used for autofocus to happen when the user takes a picture
	 * Also used to redraw the canvas using the invalidate() method
	 * when we need to redraw things
	 */
	public void onSensorChanged(SensorEvent event) {

		if (mInvalidate == true) {
			mView.invalidate();
			mInvalidate = false;
		}
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		if (!mInitialized) {
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			mInitialized = true;
		}
		float deltaX = Math.abs(mLastX - x);
		float deltaY = Math.abs(mLastY - y);
		float deltaZ = Math.abs(mLastZ - z);
		try{
			if (deltaX > .5) { 
				mPreview.setCameraFocus(myAutoFocusCallback);
			}
			if (deltaY > .5) { 
				mPreview.setCameraFocus(myAutoFocusCallback);
			}
			if (deltaZ > .5) { 
				mPreview.setCameraFocus(myAutoFocusCallback);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		mLastX = x;
		mLastY = y;
		mLastZ = z;
	}

	@Override
	protected void onDestroy() {
		Log.i(this.getClass().getSimpleName(), "onDestroy()");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		Log.i(this.getClass().getSimpleName(), "onPause()");
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_UI);
		Log.i(this.getClass().getSimpleName(), "onResume()");
	}  

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(this.getClass().getSimpleName(), "onRestart()");
	}

	@Override
	protected void onStop() {
		Log.i(this.getClass().getSimpleName(), "onStop()");
		super.onStop();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(this.getClass().getSimpleName(), "onStart()");
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}
}