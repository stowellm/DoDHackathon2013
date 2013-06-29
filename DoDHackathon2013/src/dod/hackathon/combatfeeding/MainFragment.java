package dod.hackathon.combatfeeding;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dod.hackathon.combatfeeding.objects.Day;
import dod.hackathon.combatfeeding.objects.FoodAdapter;

public class MainFragment extends Fragment {

	RelativeLayout topBarLayout;
	ProgressCircleView calProg, carbProg, fatProg, protProg;
	TextView userName, loadType, calText, carbText, fatText, protText;
	ListView loggedFoodList;
	ImageButton btnGear;
	
	// Variables retrieved from exercises
	String[] exerciseNames;
	int[] exerciseTimes;
	int caloriesBurnedFromExercise;
	
	// Variables retrieved from gear
	int gearWeight;
	
	// Variables retrieved from food
	int calories;
	int carbs;
	int fat;
	int protein;
	
	Day thisDay;

	public final int RESULT_PROFILE = 0;
	public final int RESULT_FOODPICKED = 1;
	public final int RESULT_GEAR = 2;
	public final int RESULT_EXERCISE = 3;

	Button logFood, logActivity, viewTimeline;
	
	FoodAdapter foodAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_main, container, false);
		thisDay = new Day();

		loggedFoodList = (ListView) v.findViewById(R.id.loggedFoodList);
		
		topBarLayout = (RelativeLayout) v.findViewById(R.id.bar_userinfo);
		topBarLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent iProfile = new Intent(getActivity(), ProfileDialog.class);
				startActivityForResult(iProfile, RESULT_PROFILE);
			}
		});

		btnGear = (ImageButton) v.findViewById(R.id.btn_gear);
		btnGear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent iGear = new Intent(getActivity(), GearPicker.class);
				startActivityForResult(iGear, RESULT_GEAR);
			}
			
		});
		
		userName = (TextView) v.findViewById(R.id.text_username);
		loadType = (TextView) v.findViewById(R.id.text_loadtype);

		calProg = (ProgressCircleView) v.findViewById(R.id.surface_calories);
		calProg.setColor(getResources().getColor(R.color.calories_color));
		carbProg = (ProgressCircleView) v.findViewById(R.id.surface_carbs);
		carbProg.setColor(getResources().getColor(R.color.carbs_color));
		fatProg = (ProgressCircleView) v.findViewById(R.id.surface_fat);
		fatProg.setColor(getResources().getColor(R.color.fat_color));
		protProg = (ProgressCircleView) v.findViewById(R.id.surface_protein);
		protProg.setColor(getResources().getColor(R.color.protein_color));
		
		logFood = (Button) v.findViewById(R.id.button_logfood);
		logFood.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent iFoodPicker = new Intent(getActivity(), FoodPicker.class);
				startActivityForResult(iFoodPicker, RESULT_FOODPICKED);
			}
		});
		
		logActivity = (Button) v.findViewById(R.id.button_logactivity);
		logActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent iExercisePicker = new Intent(getActivity(), ExercisePicker.class);
				iExercisePicker.putExtra("gear_weight", gearWeight);
				startActivityForResult(iExercisePicker, RESULT_EXERCISE);
			}
		});
		
		viewTimeline = (Button) v.findViewById(R.id.button_viewday);
		viewTimeline.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent iTimeline = new Intent(getActivity(), TimelineActivity.class);
				startActivity(iTimeline);
			}
		});

		calText = (TextView) v.findViewById(R.id.text_calories);
		carbText = (TextView) v.findViewById(R.id.text_carbs);
		fatText = (TextView) v.findViewById(R.id.text_fat);
		protText = (TextView) v.findViewById(R.id.text_protein);

		setupViews();
		
		return v;
	}

	public void setupViews() {

		foodAdapter = new FoodAdapter(getActivity(), R.layout.list_element_food, thisDay.getfoodLog());
		loggedFoodList.setAdapter(foodAdapter);
		
		SharedPreferences mPrefs = getActivity().getSharedPreferences("dod_hackathon", 0);

		userName.setText(mPrefs.getString("my_name", "John Doe"));
		loadType.setText(mPrefs.getString("my_loadtype", ""));

		int mHeight = mPrefs.getInt("height_inches", -1) + (mPrefs.getInt("height_feet", -1) * 12);
		int mWeightDelta = 0;
		if(mPrefs.getBoolean("target_weight_set", false)) {
			mWeightDelta = (int) (mPrefs.getFloat("my_weight", -1) - mPrefs.getFloat("target_weight", -1));
		}
		thisDay.calcRequiredValues(mPrefs.getBoolean("gender_is_male", true),
				mPrefs.getInt("age", -1),
				mHeight, 
				mPrefs.getFloat("my_weight", -1),
				mWeightDelta);

		calText.setText(Math.round(thisDay.getCalorieIntake()) + "\nout of\n" + Math.round(thisDay.getCalorieRequired()));
		carbText.setText(Math.round(thisDay.getCarbIntake()) + "\nout of\n" + Math.round(thisDay.getCarbRequired()));
		fatText.setText(Math.round(thisDay.getFatIntake()) + "\nout of\n" + Math.round(thisDay.getFatRequired()));
		protText.setText(Math.round(thisDay.getProteinIntake()) + "\nout of\n" + Math.round(thisDay.getProteinRequired()));	
		
		calProg.setProgress(((float)thisDay.getCalorieIntake() / (float)thisDay.getCalorieRequired()) * 100);
		carbProg.setProgress(((float)thisDay.getCarbIntake() / (float)thisDay.getCarbRequired()) * 100);
		fatProg.setProgress(((float)thisDay.getFatIntake() / (float)thisDay.getFatRequired()) * 100);
		protProg.setProgress(((float)thisDay.getProteinIntake() / (float)thisDay.getProteinRequired()) * 100);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == RESULT_PROFILE) {
			setupViews();
		} else if(requestCode == RESULT_FOODPICKED) {
			String foodId = data.getStringExtra("food_id");
			//thisDay.addFood(resFood);
			setupViews();
		} else if(requestCode == RESULT_GEAR) {
			
		} else if (requestCode == RESULT_EXERCISE) {
			if (data != null) {
				exerciseNames = data.getStringArrayExtra("exercise_names");
				exerciseTimes = data.getIntArrayExtra("exercise_times");
			}
		}
	}

}
