package com.utc.api01.camera;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.graphics.Bitmap.CompressFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.googlecode.leptonica.android.ReadFile;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.utc.api01.activity.MainActivity;
import com.utc.api01.activity.Results;

/**
 * Camera View
 */
public class Preview extends SurfaceView implements SurfaceHolder.Callback {	

	private SurfaceHolder mHolder;
	private Camera mCamera;
	private Camera.Parameters mParameters;
	private byte[] mBuffer;
	private BoxView boxView;
	private TessBaseAPI baseApi;
	private String path;
	private boolean processing = false;
	private boolean error = false;
	
	// this constructor used when requested as an XML resource
	public Preview(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Preview(Context context) {
		super(context);
		init();
	}

	public void init() {
		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed.
		Log.d(this.getClass().getSimpleName(), "Init preview");
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	/**
	 * Get the picture in the red box
	 */
	public Bitmap getPic(int left, int top, int right, int bottom) {
		System.gc();
		Bitmap b = null;
		Size s = mParameters.getPreviewSize();
		Log.i(this.getClass().getSimpleName(), "preview size = " + s.width + " x " + s.height + "\n" +
				"rectangle size = " + (right-left) + " x " + (bottom-top));
		YuvImage yuvimage = new YuvImage(mBuffer, ImageFormat.NV21, s.width, s.height, null);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		yuvimage.compressToJpeg(new Rect(left, top, right, bottom), 100, outStream); // make JPG
		b = BitmapFactory.decodeByteArray(outStream.toByteArray(), 0, outStream.size()); // decode JPG
		if (b != null) {
			Log.i(this.getClass().getSimpleName(), "getPic() WxH:" + b.getWidth() + "x" + b.getHeight());
		} else {
			Log.i(this.getClass().getSimpleName(), "getPic(): Bitmap is null..");
		}
		yuvimage = null;
		outStream = null;
		System.gc();
		return b;
	}

	private void updateBufferSize() {
		mBuffer = null;
		System.gc();
		// prepare a buffer for copying preview data to
		int h = mCamera.getParameters().getPreviewSize().height;
		int w = mCamera.getParameters().getPreviewSize().width;
		int bitsPerPixel = ImageFormat.getBitsPerPixel(mCamera.getParameters().getPreviewFormat());
		mBuffer = new byte[w * h * bitsPerPixel / 8];
		Log.i(this.getClass().getSimpleName(), "buffer length is " + mBuffer.length + " bytes");
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// The Surface has been created, acquire the camera and tell it where to draw.
		try {
			mCamera = Camera.open(); // WARNING: without permission in Manifest.xml, crashes
			Log.d(this.getClass().getSimpleName(), "Open camera");
			mParameters = mCamera.getParameters();
			List<String> flashModes = mParameters.getSupportedFlashModes();
			if(flashModes != null){
				for(String flash : flashModes){
					Log.d(this.getClass().getSimpleName(),"Supported flash mode : "+flash);
				}
				if(flashModes.contains(Camera.Parameters.FLASH_MODE_OFF)){
					mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
					Log.d(this.getClass().getSimpleName(),"Flash mode set to FLASH_MODE_OFF");
				}
			}else{
				Log.d(this.getClass().getSimpleName(),"No flash mode supported");
			}
			List<Size> previewSizes = mParameters.getSupportedPreviewSizes();
			Size optimalPreviewSize = getOptimalPreviewSize(previewSizes, getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
			Log.d(this.getClass().getSimpleName(),"Preview size set to "+optimalPreviewSize.width+"*"+optimalPreviewSize.height);
			List<Size> pictureSizes = mParameters.getSupportedPictureSizes();
			Size optimalPictureSize = getOptimalPictureSize(pictureSizes, getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
			Log.d(this.getClass().getSimpleName(),"Picture size set to "+optimalPictureSize.width+"*"+optimalPictureSize.height);
			mParameters.setPreviewSize(optimalPreviewSize.width, optimalPreviewSize.height);
			mParameters.setPictureSize(optimalPictureSize.width, optimalPictureSize.height);
			List<String> focusModes = mParameters.getSupportedFocusModes();
			for(String mode : focusModes){
				Log.d(this.getClass().getSimpleName(),"Supported focus mode : "+mode);
			}
			if(focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)){
				mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
				Log.d(this.getClass().getSimpleName(),"Focus mode set to CONTINUOUS_PICTURE");
			}else if(focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)){
				mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
				Log.d(this.getClass().getSimpleName(),"Focus mode set to AUTO");
				setFocusArea();
			}else if(focusModes.contains(Camera.Parameters.FOCUS_MODE_MACRO)){
				mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_MACRO);
				Log.d(this.getClass().getSimpleName(),"Focus mode set to MACRO");
				setFocusArea();
			}
			mCamera.setParameters(mParameters);
		}
		catch (RuntimeException exception) {
			Log.e(this.getClass().getSimpleName(), "Exception on Camera.open(): " + exception.toString());
			Toast.makeText(getContext(), "Camera broken, quitting :(",Toast.LENGTH_LONG).show();
			error = true;
		}
		
		try {
			mCamera.setPreviewDisplay(holder);
			updateBufferSize();
			mCamera.addCallbackBuffer(mBuffer); // where we'll store the image data
			mCamera.setPreviewCallbackWithBuffer(new PreviewCallback() {
				public synchronized void onPreviewFrame(byte[] data, Camera c) {

					if (mCamera != null) { // there was a race condition when onStop() was called..
						mCamera.addCallbackBuffer(mBuffer); // it was consumed by the call, add it back
					}
					if(!processing){
						processing = true;
						readBook();
						processing = false;
					}
				}
			});
		} catch (Exception exception) {
			Log.e(this.getClass().getSimpleName(), "Exception trying to set preview");
			if (mCamera != null){
				mCamera.release();
				mCamera = null;
			}
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// Surface will be destroyed when we return, so stop the preview.
		// Because the CameraDevice object is not a shared resource, it's very
		// important to release it when the activity is paused.
		Log.i(this.getClass().getSimpleName(),"SurfaceDestroyed being called");
		mCamera.stopPreview();
		mCamera.release();
		mCamera = null;
	}

	// FYI: not called for each frame of the camera preview
	// gets called on my phone when keyboard is slid out
	// requesting landscape orientation prevents this from being called as camera tilts
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		Log.i(this.getClass().getSimpleName(), "Preview: surfaceChanged() - size now " + w + "x" + h);
		Log.i(this.getClass().getSimpleName(), "this.size =  " + this.getWidth() + "x" + this.getHeight());
		if(error){
			Intent i = new Intent();
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			i.setClass(getContext(), MainActivity.class);
			getContext().startActivity(i);
			Log.e(this.getClass().getSimpleName(), "Error on camera.open() return to menu");
		}
		// Now that the size is known, set up the camera parameters and begin
		// the preview.
		try {
			mParameters = mCamera.getParameters();
			mParameters.set("orientation","portrait");
			for (Integer i : mParameters.getSupportedPreviewFormats()) {
				Log.i(this.getClass().getSimpleName(), "supported preview format: " + i);
			} 
			mCamera.setParameters(mParameters); // apply the changes
		} catch (Exception e) {
			Log.d(this.getClass().getSimpleName(), e.getMessage());
			// older phone - doesn't support these calls
		}

		updateBufferSize(); // then use them to calculate
		
		Size p = mCamera.getParameters().getPreviewSize();
		Log.d(this.getClass().getSimpleName(), "Preview: checking it was set: " + p.width + "x" + p.height);

		if (boxView != null) {
			boxView.setBoxPosition(h, w, 0.5, p);
		}
		mCamera.startPreview();
	}

	public Parameters getCameraParameters(){
		return mCamera.getParameters();
	}
	
	public Camera getCamera(){
		return mCamera;
	}

	public void setCameraFocus(AutoFocusCallback autoFocus){
		if (mCamera.getParameters().getFocusMode().equals(Parameters.FOCUS_MODE_AUTO) ||
				mCamera.getParameters().getFocusMode().equals(Parameters.FOCUS_MODE_MACRO)){
			mCamera.autoFocus(autoFocus);
		}
	}
	
	/**
	 * Get optimal preview size based on aspect ratio of device
	 * @param sizes
	 * @param w
	 * @param h
	 * @return
	 */
	private Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio=(double)h / w;

        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Camera.Size size : sizes) {
        	Log.d(this.getClass().getSimpleName(), "Supported preview size: " + size.width + "x" + size.height);
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }
	
	/**
	 * Get optimal picture size based on aspect ratio of device
	 * @param sizes
	 * @param w
	 * @param h
	 * @return
	 */
	private Size getOptimalPictureSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        final int maxRes = 5250000;
        double targetRatio=(double)h / w;

        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        int res = 0;

        for (Camera.Size size : sizes) {
        	Log.d(this.getClass().getSimpleName(), "Supported picture size: " + size.width + "x" + size.height);
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (size.height*size.width > res && size.height*size.width <= maxRes) {
                optimalSize = size;
                res = size.height*size.width;
            }
        }

        if (optimalSize == null) {
        	res = 0;
            for (Camera.Size size : sizes) {
            	if (size.height*size.width > res && size.height*size.width <= maxRes) {
                    optimalSize = size;
                    res = size.height*size.width;
                }
            }
        }
        return optimalSize;
    }
	
	/**
	 * Set focus area if focus mode is not continuous
	 */
	private void setFocusArea(){
		if (mParameters.getMaxNumMeteringAreas() > 0){ // check that metering areas are supported
		    List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
		    Rect areaRect1 = new Rect(-200, -200, 200, 200);
		    meteringAreas.add(new Camera.Area(areaRect1, 1000));
		    mParameters.setMeteringAreas(meteringAreas);
		}
		if (mParameters.getMaxNumFocusAreas() > 0){ // check that focus areas are supported
		    List<Camera.Area> focusAreas = new ArrayList<Camera.Area>();
		    Rect areaRect1 = new Rect(-200, -200, 200, 200);
		    focusAreas.add(new Camera.Area(areaRect1, 1000));
		    mParameters.setFocusAreas(focusAreas);
		}
	}

	
	/**
	 * Read the number
	 */
	private void readBook(){
		Size s = mParameters.getPreviewSize();
		Bitmap bitmap = getPic((int)(boxView.getmLeftBottomPosX()*s.width/getWidth()),(int)(boxView.getmRightTopPosY()*s.height/getHeight()),(int)(boxView.getmRightBottomPosX()*s.width/getWidth()),(int)(boxView.getmRightBottomPosY()*s.height/getHeight()));
		baseApi.setImage(ReadFile.readBitmap(bitmap));
		String ocr = baseApi.getUTF8Text();
		Log.d(this.getClass().getSimpleName(), "Ocr = "+ocr);
		//TODO check validity
		if(ocr.contains("978-0-19-452953-2")){
			mCamera.stopPreview();
			savePic(bitmap,true);
			Intent i = new Intent();
			i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			i.putExtra("RESULT", ocr);
			i.setClass(getContext(), Results.class);
			getContext().startActivity(i);
		}else{
			savePic(bitmap,false);
		}
	}

	/**
	 * Save the picture on the sd card
	 * @param bitmap
	 * @param valid
	 */
	private void savePic(Bitmap bitmap, boolean valid){
		String dateStr = ((Long)System.currentTimeMillis()).toString();
		FileOutputStream image = null;
		String path = this.path+"/";
		File filePath = new File(path);
		if(!filePath.exists()){
			filePath.mkdir();
		}
		String location = path+dateStr+"_"+valid+".jpg";
		try {
			image = new FileOutputStream(location);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bitmap.compress(CompressFormat.JPEG, 100, image);
	}
	
	public void setBoxView (BoxView view) {
		this.boxView = view;
	}	

	public void setBaseApi(TessBaseAPI baseApi) {
		this.baseApi = baseApi;
	}
	
	public void setPath(String path){
		this.path = path;
	}
}