package com.utc.api01.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BoxView extends View {
	
	private float mLeftTopPosX = 30;
	private float mLeftTopPosY = 120;

	private float mRightTopPosX = 150;
	private float mRightTopPosY = 120;

	private float mLeftBottomPosX = 30;
	private float mLeftBottomPosY = 200;

	private float mRightBottomPosX = 150;
	private float mRightBottomPosY = 200;

	private Paint topLine;
	private Paint bottomLine;
	private Paint leftLine;
	private Paint rightLine;
	
	private float parentWidth;
	private float parentHeight;
	

	public BoxView(Context context){
		super(context);
		init(context);
	}

	public BoxView(Context context, AttributeSet attrs){
		super (context,attrs);
		init(context);
	}

	public BoxView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		topLine = new Paint();
		bottomLine = new Paint();
		leftLine = new Paint();
		rightLine = new Paint();
		setLineParameters(Color.GREEN,5);
	}

	private void setLineParameters(int color, float width){

		topLine.setColor(color);
		topLine.setStrokeWidth(width);

		bottomLine.setColor(color);
		bottomLine.setStrokeWidth(width);

		leftLine.setColor(color);
		leftLine.setStrokeWidth(width);

		rightLine.setColor(color);
		rightLine.setStrokeWidth(width);
	
	}

	// Draws the bounding box on the canvas. Every time invalidate() is called
	// this onDraw method is called.
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		canvas.drawLine(mLeftTopPosX, mLeftTopPosY,
				mRightTopPosX, mRightTopPosY, topLine);
		canvas.drawLine(mLeftBottomPosX, mLeftBottomPosY,
				mRightBottomPosX, mRightBottomPosY, bottomLine);
		canvas.drawLine(mLeftTopPosX,mLeftTopPosY,
				mLeftBottomPosX,mLeftBottomPosY,leftLine);
		canvas.drawLine(mRightTopPosX,mRightTopPosY,
				mRightBottomPosX,mRightBottomPosY,rightLine);
	}


	public float getmLeftTopPosX(){
		return mLeftTopPosX;
	}
	public float getmLeftTopPosY(){
		return mLeftTopPosY;
	}
	public float getmRightTopPosX(){
		return mRightTopPosX;
	}
	public float getmRightTopPosY(){
		return mRightTopPosY;
	}
	public float getmLeftBottomPosX() {
		return mLeftBottomPosX;
	}
	public float getmLeftBottomPosY() {
		return mLeftBottomPosY;
	}
	public float getmRightBottomPosY() {
		return mRightBottomPosY;
	}
	public float getmRightBottomPosX() {
		return mRightBottomPosX;
	}

	public void setInvalidate() {
		invalidate();	
	}
	
	/**
	 * Calculate and set the box position. Set a 50% minimum margin.
	 * @param height : the height of the VIEW containing the box
	 * @param width : the width of the VIEW containing the box
	 * @param ratio : ratio height/width of the BOX
	 */
	public void setBoxPosition(float height, float width, double ratio, Size size) {
		this.parentHeight = height;
		this.parentWidth = width;
		
		double boxHeight;
		double boxWidth;
		float viewRatio = (float)size.width/(float)size.height;
		float parentRatio = (float)width/(float)height;
		Log.d(this.getClass().getSimpleName(), "viewRatio = "+viewRatio);
		Log.d(this.getClass().getSimpleName(),"parentRatio = "+parentRatio);
		Log.d(this.getClass().getSimpleName(),"ratio before correction = "+ratio);
		ratio *= viewRatio/parentRatio;
		Log.d(this.getClass().getSimpleName(),"ratio after correction = "+ratio);
		if (height/ratio < width ) {
			boxHeight = 0.5*height;
			boxWidth = boxHeight/ratio;
		} else {
			boxWidth = 0.5*width;
			boxHeight = boxWidth*ratio;
		}
		
		Log.d(this.getClass().getSimpleName(),"height = "+height);
		Log.d(this.getClass().getSimpleName(),"width = "+width);
		Log.d(this.getClass().getSimpleName(),"boxHeight = "+boxHeight);
		Log.d(this.getClass().getSimpleName(),"boxWidth = "+boxWidth);
		
		mLeftTopPosX = (float) ((width - boxWidth)/2);
		mLeftTopPosY = (float) ((height - boxHeight)/2);
		
		mRightTopPosX = (float) ((width + boxWidth)/2);
		mRightTopPosY = (float) ((height - boxHeight)/2);

		mLeftBottomPosX = (float) ((width - boxWidth)/2);
		mLeftBottomPosY = (float) ((height + boxHeight)/2);

		mRightBottomPosX = (float) ((width + boxWidth)/2);
		mRightBottomPosY = (float) ((height + boxHeight)/2);
		
		Log.d(this.getClass().getSimpleName(), "mLeftTopPosX = " + mLeftTopPosX + " mLeftTopPosY = " + mLeftTopPosY + " mRightTopPosX = " +
				mRightTopPosX + " mRightTopPosY = " + mRightTopPosY + " mLeftBottomPosX = " + mLeftBottomPosX 
				+ " mLeftBottomPosY = " + mLeftBottomPosY + " mRightBottomPosX = " + mRightBottomPosX 
				+ " mRightBottomPosY = " + mRightBottomPosY);
		
		setInvalidate();
	}
	
	public float getParentWidth() {
		return this.parentWidth;
	}
	
	public float getParentHeight() {
		return this.parentHeight;
	}
}