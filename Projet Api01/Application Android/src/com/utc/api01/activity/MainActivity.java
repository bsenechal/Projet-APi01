package com.utc.api01.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.utc.api01.listener.CloseListener;

import cc.tamerelachauve.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Main page
 */

public class MainActivity extends Activity
{
	private static final String[] LANGUAGE = {"fra"};
	
	/**
	 * Called when the activity is created
	 */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.menu);

      File path = getStorageDirectory();

      if(!copyAssets(path)){
    	  showErrorMessage("Error", "Can't copy trained data on SD Card");
      }
      final Button button = (Button) findViewById(R.id.menu_button);
      button.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent();
	        i.setClass(v.getContext(), CameraPreview.class);
	        startActivity(i);
		}
	});
   }
	
	/** 
	 * Finds the location on the SD card where we can save files. 
	 * 
	 * @return File The location on the SD card
	 */
	private File getStorageDirectory() {
		//Check the state of the SD card (should be mounted)
		String errorMessage = "Required external storage (such as an SD card) is unavailable.";
	    String state = null;
	    try {
	    	state = Environment.getExternalStorageState();
	    } catch(RuntimeException e){
	    	Log.e(this.getClass().getSimpleName(), "SD Card is unavailable.", e);
	    	showErrorMessage("Error", errorMessage);
	    }
	    if(Environment.MEDIA_MOUNTED.equals(state)){
	    	try {
	    		return getExternalFilesDir(Environment.MEDIA_MOUNTED);
	    	} catch(NullPointerException e){
		        Log.e(this.getClass().getSimpleName(), "SD Card is unavailable", e);
		        showErrorMessage("Error", errorMessage);
	    	}
	    } else {
	    	//If the state isn't mounted, show an error message
	    	Log.e(this.getClass().getSimpleName(), "SD Card is unavailable, state = "+state);
	    	showErrorMessage("Error", errorMessage);
	    }
	    return null;
	}
	
	/**
	 * Copy the trained data for ocr
	 * 
	 * @param path The path on the SD card
	 * @return true if the copy was done, otherwise false
	 */
	private boolean copyAssets(File path){
		AssetManager assetManager = getAssets();
		//Check if the tessdata directory already exists
		String tessDataPath = path.getAbsolutePath() + "/tessdata/";
		if(!new File(tessDataPath).exists()){
			new File(tessDataPath).mkdirs();
		}
		//For each language, we copy the trained data on the SD card in the tessdata directory
		for(String lang : LANGUAGE){
			String outPath = tessDataPath + lang + ".traineddata";
			if(!(new File(outPath)).exists()) {
				try {
					InputStream in = assetManager.open("tessdata/" + lang + ".traineddata");
					OutputStream out = new FileOutputStream(outPath);
					// Transfer bytes from in to out
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					in.close();
					out.close();
					Log.d(this.getClass().getSimpleName(), "Copied " + lang + " traineddata");
				} catch (IOException e) {
					Log.e(this.getClass().getSimpleName(), "Was unable to copy " + lang + " traineddata " + e.toString());
					return false;
				}
			}
		}
		return true;
	}
	  
	/**
	* Displays an error message dialog box to the user.
	* 
	* @param title The title for the dialog box
	* @param message The error message to be displayed
	*/
	void showErrorMessage(String title, String message) {
		new AlertDialog.Builder(this)
		.setTitle(title)
		.setMessage(message)
		.setOnCancelListener(new CloseListener(this))
	    .setPositiveButton( "Done", new CloseListener(this))
		.show();
	}
}