package dod.hackathon.combatfeeding;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ProgressCircleView extends SurfaceView implements SurfaceHolder.Callback {

	float progress;
	
	public ProgressCircleView(Context context, AttributeSet attributeSet) {
	    super(context, attributeSet);
	    getHolder().addCallback(this);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
