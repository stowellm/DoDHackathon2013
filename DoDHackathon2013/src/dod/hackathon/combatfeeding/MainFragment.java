package dod.hackathon.combatfeeding;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import dod.hackathon.combatfeeding.objects.Day;

public class MainFragment extends Fragment {

	ProgressCircleView calProg, carbProg, fatProg, protProg;
	Day thisDay;
	Button logFood, logActivity;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_main, container, false);

		calProg = (ProgressCircleView) v.findViewById(R.id.surface_calories);
		carbProg = (ProgressCircleView) v.findViewById(R.id.surface_carbs);
		fatProg = (ProgressCircleView) v.findViewById(R.id.surface_fat);
		protProg = (ProgressCircleView) v.findViewById(R.id.surface_protein);
		
		logFood = (Button) v.findViewById(R.id.button_logfood);
		logFood.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent iFoodPicker = new Intent(getActivity(), FoodPicker.class);
				startActivity(iFoodPicker);
			}
		});
		
		logActivity = (Button) v.findViewById(R.id.button_logactivity);
		
		return v;
	}

}
