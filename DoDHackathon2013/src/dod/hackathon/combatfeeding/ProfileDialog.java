package dod.hackathon.combatfeeding;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;

public class ProfileDialog extends Activity {

	private Button male, female, cancel, ok;
	private SeekBar feetSeek, inchesSeek;
	private CheckBox targetWeightCheck;
	private EditText weightLbs, targetWeightLbs, age;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_profile);
		
		male = (Button) findViewById(R.id.dialog_profile_male);
		male.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				male.setAlpha(1.0f);
				female.setAlpha(0.5f);
			}
		});
		
		female = (Button) findViewById(R.id.dialog_profile_female);
		female.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				male.setAlpha(0.5f);
				female.setAlpha(1.0f);
			}
		});
		female.setAlpha(0.5f);
		
	}

	@Override
	public void onBackPressed() {
		setResult(RESULT_CANCELED);
		finish();
	}
}
