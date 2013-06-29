package dod.hackathon.combatfeeding;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import dod.hackathon.combatfeeding.objects.Day;

public class MainFragment extends Fragment {

	ProgressCircleView calProg, carbProg, fatProg, protProg;
	TextView calText, carbText, fatText, protText;
	
	Day thisDay;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_main, container, false);
		thisDay = new Day();
		
		SharedPreferences mPrefs = getActivity().getSharedPreferences("dod_hackathon", 0);
		
		int mHeight = mPrefs.getInt("height_inches", -1) + (mPrefs.getInt("height_feet", -1) * 12);
		int mWeightDelta = 0;
		if(mPrefs.getBoolean("target_weight_set", false)) {
			mWeightDelta = mPrefs.getInt("my_weight", -1) - mPrefs.getInt("target_weight", -1);
		}
		thisDay.calcRequiredValues(mPrefs.getBoolean("gender_is_male", true),
								   mPrefs.getInt("age", -1),
								   mHeight, 
								   mPrefs.getInt("my_weight", -1),
								   mWeightDelta);

		calProg = (ProgressCircleView) v.findViewById(R.id.surface_calories);
		carbProg = (ProgressCircleView) v.findViewById(R.id.surface_carbs);
		fatProg = (ProgressCircleView) v.findViewById(R.id.surface_fat);
		protProg = (ProgressCircleView) v.findViewById(R.id.surface_protein);
		
		calText = (TextView) v.findViewById(R.id.text_calories);
		carbText = (TextView) v.findViewById(R.id.text_carbs);
		fatText = (TextView) v.findViewById(R.id.text_fat);
		protText = (TextView) v.findViewById(R.id.text_protein);
		
		calText.setText(thisDay.getCalorieIntake() + "\nout of\n" + thisDay.getCalorieRequired());
		carbText.setText(thisDay.getCarbIntake() + "\nout of\n" + thisDay.getCarbRequired());
		fatText.setText(thisDay.getFatIntake() + "\nout of\n" + thisDay.getFatRequired());
		protText.setText(thisDay.getProteinIntake() + "\nout of\n" + thisDay.getProteinRequired());
		
		
		
		return v;
	}

}
