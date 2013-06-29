package dod.hackathon.combatfeeding;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
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

	private SharedPreferences mPrefs;
	private SharedPreferences.Editor mEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_profile);

		mPrefs = getSharedPreferences("time_offset", 0);
		mEdit = mPrefs.edit();

		male = (Button) findViewById(R.id.dialog_profile_male);
		male.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				male.setAlpha(1.0f);
				male.setBackgroundColor(Color.parseColor("#DDDDDD"));
				female.setBackgroundColor(Color.parseColor("#FFFFFF"));
				female.setAlpha(0.5f);
			}
		});

		female = (Button) findViewById(R.id.dialog_profile_female);
		female.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				male.setAlpha(0.5f);
				female.setAlpha(1.0f);
				male.setBackgroundColor(Color.parseColor("#FFFFFF"));
				female.setBackgroundColor(Color.parseColor("#DDDDDD"));
			}
		});
		female.setAlpha(0.5f);
		female.setBackgroundColor(Color.parseColor("#DDDDDD"));

	}

	@Override
	public void onBackPressed() {
		setResult(RESULT_CANCELED);
		finish();
	}
}
