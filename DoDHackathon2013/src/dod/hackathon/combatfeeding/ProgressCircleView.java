package dod.hackathon.combatfeeding;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ProgressCircleView extends SurfaceView implements SurfaceHolder.Callback {

	private float progress;
	private float visibleProgress;
	private int drawColor;
	ProgressCircleThread mThread;
	
	public ProgressCircleView(Context context, AttributeSet attributeSet) {
	    super(context, attributeSet);
	    getHolder().addCallback(this);
	    visibleProgress = 0;
	    progress = 0;
	}
	
	public void setColor(int newColor) {
		drawColor = newColor;
	}
	
	public boolean setProgress(float newProgress) {
		if(newProgress >= 0 && newProgress <= 100) {
			progress = newProgress;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		//TODO - implement this?
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mThread = new ProgressCircleThread(holder, getContext(), this);
		mThread.setRunning(true);
		mThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mThread.setRunning(false);
		boolean retry = true;
		while(retry) {
			try {
				mThread.join();
				retry = false;
			} catch( Exception e ) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    
	    //Forces the view to be square, based off of the width in the layout
	    this.setMeasuredDimension(
	    		widthMeasureSpec, widthMeasureSpec);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		canvas.drawColor(getResources().getColor(R.color.lightgrey));
		
		Paint p = new Paint();
		p.setAntiAlias(true);
		p.setColor(Color.DKGRAY);
		
		p.setStrokeWidth(20);
		p.setStrokeCap(Paint.Cap.BUTT);
		p.setStyle(Style.STROKE);
		
		//TODO - make this take a consistant amount of time to finish
		if(visibleProgress < progress) {
			visibleProgress += 0.4;
		} else if(visibleProgress > progress) {
			visibleProgress -= 0.4;
		}
		float degreesOut = 360 * (visibleProgress/100);
		
		RectF r = new RectF(22.5f, 22.5f, getHolder().getSurfaceFrame().width()-22.5f, getHolder().getSurfaceFrame().width()-22.5f);
		canvas.drawArc(r, 0, 360, false, p);
		
		p.setColor(drawColor);
		canvas.drawArc(r, -90, -degreesOut, false, p);
		
	}
	
	public class ProgressCircleThread extends Thread {
		Canvas mCanvas;
		SurfaceHolder mSurfaceHolder;
		Context mContext;
		ProgressCircleView mProgCircle;
		
		boolean isRunning;
		
		public ProgressCircleThread(SurfaceHolder sH, Context c, ProgressCircleView pCV) {
			mSurfaceHolder = sH;
			mContext = c;
			mProgCircle = pCV;
			isRunning = false;
		}
		
		void setRunning(boolean r) {
			isRunning = r;
		}
		
		@Override
		public void run() {
			super.run();
			while(isRunning) {
				mCanvas = mSurfaceHolder.lockCanvas();
				if(mCanvas != null) {
					mProgCircle.draw(mCanvas);
					mSurfaceHolder.unlockCanvasAndPost(mCanvas);
				}
			}
		}
		
	}
}
