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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import dod.hackathon.combatfeeding.objects.Day;
import dod.hackathon.combatfeeding.objects.FoodAdapter;

public class MainFragment extends Fragment {

	LinearLayout topBarLayout;
	ProgressCircleView calProg, carbProg, fatProg, protProg;
	TextView userName, calText, carbText, fatText, protText;
	ListView loggedFoodList;
	
	Day thisDay;

	public final int RESULT_PROFILE = 0;
	public final int RESULT_FOODPICKED = 1;

	Button logFood, logActivity, viewTimeline;
	
	FoodAdapter foodAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_main, container, false);
		thisDay = new Day();

		loggedFoodList = (ListView) v.findViewById(R.id.loggedFoodList);
		
		topBarLayout = (LinearLayout) v.findViewById(R.id.bar_userinfo);
		topBarLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent iProfile = new Intent(getActivity(), ProfileDialog.class);
				startActivityForResult(iProfile, RESULT_PROFILE);
			}
		});

		userName = (TextView) v.findViewById(R.id.text_username);

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

		calText.setText(thisDay.getCalorieIntake() + "\nout of\n" + thisDay.getCalorieRequired());
		carbText.setText(thisDay.getCarbIntake() + "\nout of\n" + thisDay.getCarbRequired());
		fatText.setText(thisDay.getFatIntake() + "\nout of\n" + thisDay.getFatRequired());
		protText.setText(thisDay.getProteinIntake() + "\nout of\n" + thisDay.getProteinRequired());	
		
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
			//Food resFood = (Food) data.getParcelableExtra("food");
			//thisDay.addFood(resFood);
			setupViews();
		}
	}

}
