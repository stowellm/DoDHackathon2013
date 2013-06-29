package dod.hackathon.combatfeeding;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ProfileDialog extends Activity {

	private Button male, female, cancel, ok;
	private SeekBar feetSeek, inchesSeek;
	private TextView feetLabel, inchesLabel, myWeightLabel, targetWeightLabel,
			myTargetLbs;
	private CheckBox targetWeightCheck;
	private EditText nameBox, weightLbs, targetLbs, age;

	private SharedPreferences mPrefs;
	private SharedPreferences.Editor mEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_profile);

		mPrefs = getSharedPreferences("dod_hackathon", 0);
		mEdit = mPrefs.edit();

		LinearLayout ll = (LinearLayout) findViewById(R.id.dialog_profile_main_layout);
		ll.requestFocus();

		nameBox = (EditText) findViewById(R.id.dialog_profile_user_name);
		nameBox.setText(mPrefs.getString("my_name", ""));
		
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

		boolean isMale = mPrefs.getBoolean("gender_is_male", true);
		if (isMale) {
			male.setAlpha(1.0f);
			male.setBackgroundColor(Color.parseColor("#DDDDDD"));
			female.setBackgroundColor(Color.parseColor("#FFFFFF"));
			female.setAlpha(0.5f);
		} else {
			male.setAlpha(0.5f);
			female.setAlpha(1.0f);
			male.setBackgroundColor(Color.parseColor("#FFFFFF"));
			female.setBackgroundColor(Color.parseColor("#DDDDDD"));
		}

		cancel = (Button) findViewById(R.id.dialog_profile_cancel);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});

		boolean firstSetup = mPrefs.getBoolean("first_setup", true);
		if (firstSetup) {
			cancel.setVisibility(View.GONE);
			setFinishOnTouchOutside(false);
		}

		ok = (Button) findViewById(R.id.dialog_profile_ok);
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean ready = true;

				if(nameBox.getText().toString().length() == 0) {
					nameBox.setError("Please enter a name");
					ready = false;
				} else {
					nameBox.setError(null);
					mEdit.putString("my_name", nameBox.getText().toString()).commit();
				}
				
				if (male.getAlpha() == 1.0f)
					mEdit.putBoolean("gender_is_male", true).commit();
				else
					mEdit.putBoolean("gender_is_male", false).commit();

				if (weightLbs.getText().toString().length() == 0) {
					weightLbs.setError("Please enter a weight");
					ready = false;
				} else {
					weightLbs.setError(null);
					float userWeight = Float.parseFloat(weightLbs.getText()
							.toString());
					mEdit.putFloat("my_weight", userWeight).commit();
				}

				if (targetWeightCheck.isChecked()) {
					mEdit.putBoolean("target_weight_set", true).commit();

					if (targetLbs.getText().toString().length() == 0) {
						targetLbs.setError("Please enter a target weight");
						ready = false;
					} else {
						targetLbs.setError(null);
						float userTargetWeight = Float.parseFloat(targetLbs
								.getText().toString());
						mEdit.putFloat("target_weight", userTargetWeight)
								.commit();
					}
				} else {
					mEdit.putBoolean("target_weight_set", false).commit();
				}

				int heightFeet = feetSeek.getProgress() + 4;
				int heightInches = inchesSeek.getProgress();
				mEdit.putInt("height_feet", heightFeet).commit();
				mEdit.putInt("height_inches", heightInches).commit();

				if (age.getText().toString().length() == 0) {
					age.setError("Please enter your age");
					ready = false;
				} else {
					age.setError(null);
					int myAge = Integer.parseInt(age.getText().toString());
					mEdit.putInt("age", myAge).commit();
				}

				if (ready) {
					mEdit.putBoolean("first_setup", false).commit();
					setResult(RESULT_OK);
					finish();
				}
			}
		});

		feetSeek = (SeekBar) findViewById(R.id.dialog_profile_height_feet_seeker);
		inchesSeek = (SeekBar) findViewById(R.id.dialog_profile_height_inches_seeker);

		feetLabel = (TextView) findViewById(R.id.dialog_profile_height_feet_label);
		inchesLabel = (TextView) findViewById(R.id.dialog_profile_height_inches_label);

		feetSeek.incrementProgressBy(1);
		feetSeek.setMax(4);
		feetSeek.setProgress(-1);
		feetSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					if (progress >= 0 && progress <= feetSeek.getMax()) {

						String progressString = String.valueOf(progress + 4);
						feetLabel.setText(progressString + " ft.");
						seekBar.setSecondaryProgress(progress);
					}
				}

			}
		});

		inchesSeek.incrementProgressBy(1);
		inchesSeek.setMax(11);
		inchesSeek.setProgress(-1);
		inchesSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					if (progress >= 0 && progress <= inchesSeek.getMax()) {

						String progressString = String.valueOf(progress);
						inchesLabel.setText(progressString + " in.");
						seekBar.setSecondaryProgress(progress);
					}
				}

			}
		});

		int userHeightFt = mPrefs.getInt("height_feet", -1);
		if (userHeightFt == -1) {
			feetLabel.setText("4 ft.");
			inchesLabel.setText("0 in.");
		} else {
			int userHeightIn = mPrefs.getInt("height_inches", -1);
			feetLabel.setText(userHeightFt + " in.");
			inchesLabel.setText(userHeightIn + " in.");
			feetSeek.setProgress(userHeightFt - 4);
			inchesSeek.setProgress(userHeightIn);
		}

		myWeightLabel = (TextView) findViewById(R.id.dialog_profile_my_weight_label);
		targetWeightLabel = (TextView) findViewById(R.id.dialog_profile_my_target_label);
		myTargetLbs = (TextView) findViewById(R.id.dialog_profile_my_target_lbs);

		weightLbs = (EditText) findViewById(R.id.dialog_profile_my_weight);
		targetLbs = (EditText) findViewById(R.id.dialog_profile_my_target);

		float myWeight = mPrefs.getFloat("my_weight", -1.0f);
		if (myWeight != -1.0f)
			weightLbs.setText("" + myWeight);

		targetWeightCheck = (CheckBox) findViewById(R.id.dialog_profile_target_weight_checkbox);
		targetWeightCheck
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							targetWeightLabel.setVisibility(View.VISIBLE);
							targetLbs.setVisibility(View.VISIBLE);
							myWeightLabel.setVisibility(View.VISIBLE);
							targetWeightLabel.setVisibility(View.VISIBLE);
							myTargetLbs.setVisibility(View.VISIBLE);
						} else {
							targetWeightLabel.setVisibility(View.INVISIBLE);
							targetLbs.setVisibility(View.INVISIBLE);
							myWeightLabel.setVisibility(View.INVISIBLE);
							targetWeightLabel.setVisibility(View.INVISIBLE);
							myTargetLbs.setVisibility(View.INVISIBLE);
						}
					}
				});

		boolean targetWeightSet = mPrefs.getBoolean("target_weight_set", false);
		if (targetWeightSet) {
			targetWeightCheck.toggle();

			float userTargetWeight = mPrefs.getFloat("target_weight", -1.0f);
			if (userTargetWeight != -1)
				targetLbs.setText("" + userTargetWeight);
		} else {
			targetWeightLabel.setVisibility(View.INVISIBLE);
			targetLbs.setVisibility(View.INVISIBLE);
			myWeightLabel.setVisibility(View.INVISIBLE);
			targetWeightLabel.setVisibility(View.INVISIBLE);
			myTargetLbs.setVisibility(View.INVISIBLE);
		}

		age = (EditText) findViewById(R.id.dialog_profile_my_age);

		int myAge = mPrefs.getInt("age", -1);
		if (myAge != -1)
			age.setText("" + myAge);

	}

	@Override
	public void onBackPressed() {
		boolean firstSetup = mPrefs.getBoolean("first_setup", true);
		if (!firstSetup) {
			setResult(RESULT_CANCELED);
			finish();
		}
	}
}
