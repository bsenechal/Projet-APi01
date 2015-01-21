package com.utc.api01.activity;

import cc.tamerelachauve.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Result activity
 */
public class Results extends Activity {
	
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      //Retrieve and display the informations shared by all the result views
	      setContentView(R.layout.results);
	      String result = getIntent().getExtras().getString("RESULT");
	      ((TextView) (findViewById(R.id.results_textViewOcr))).setText(result);
	   }

	   /**
	    * Return to the main activity when the user press the back button
	    */
	   public void onBackPressed(){
		   Intent i = new Intent();
		   i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		   i.setClass(this, MainActivity.class);
		   startActivity(i);
	   }
}
