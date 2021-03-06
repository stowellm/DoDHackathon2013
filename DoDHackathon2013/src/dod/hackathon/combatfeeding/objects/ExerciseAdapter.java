package dod.hackathon.combatfeeding.objects;

/* PLEASE READ THE NOTE AT THE BOTTOM OF THE PAGE */

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import dod.hackathon.combatfeeding.R;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {

	public ArrayList<Exercise> exercises;
	private Context context;
	private int layoutId;
	public ArrayList<Integer> placeholder;
	public int gearWeight;
	public float userWeight;

	public ExerciseAdapter(Context context, int textViewResourceId,
			ArrayList<Exercise> exercises, int gearWeight, float userWeight) {
		super(context, textViewResourceId);

		this.context = context;
		this.exercises = exercises;
		this.gearWeight = 100; // TODO this.gearWeight = gearWeight;
		this.userWeight = userWeight;
		
		placeholder = new ArrayList<Integer>();
		for (@SuppressWarnings("unused") Exercise e : exercises)
			placeholder.add(0);
		
		layoutId = textViewResourceId;

	}

	// For this helper method, return based on filteredData
	public int getCount() {
		return exercises.size();
	}

	// This should return a data object, not an int
	public Exercise getItem(int position) {
		return exercises.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = convertView;
		if (convertView == null) {
			view = inflater.inflate(layoutId, null);
		}

		TextView name = (TextView) view.findViewById(R.id.list_element_exercise_name);
		name.setText("" + exercises.get(position).get("EXERCISES"));
		
		Spinner spin = (Spinner) view.findViewById(R.id.list_element_exercise_spin);
		String choices[] = new String[25];
		choices[0] = ""; 	 choices[1] = "0000"; choices[2] = "0100"; choices[3] = "0200";
		choices[4] = "0300"; choices[5] = "0400"; choices[6] = "0500"; choices[7] = "0600";
		choices[8] = "0700"; choices[9] = "0800"; choices[10] = "0900"; choices[11] = "1000";
		choices[12] = "1100"; choices[13] = "1200"; choices[14] = "1300"; choices[15] = "1400";
		choices[16] = "1500"; choices[17] = "1600"; choices[18] = "1700"; choices[19] = "1800";
		choices[20] = "1900"; choices[21] = "2000"; choices[22] = "2100"; choices[23] = "2200";
		choices[24] = "2300";
		spin = (Spinner) view.findViewById(R.id.list_element_exercise_spin);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, 
				android.R.layout.simple_spinner_item, choices);
		spin.setAdapter(adapter);
		spin.setSelection(placeholder.get(position));
		final int mPos = position;
		spin.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int pos, long id) {
				placeholder.set(mPos, pos);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		/*
		 * VERY IMPORTANT NOTE PLEASE READ THIS THING OR ELSE YOUR CODE WILL BE BAD:
		 * 
		 * Since we do not currently calculate duration of an exercise, this calorie
		 * formula will assume one hour for all exercises.
		 */
		TextView cal = (TextView) view.findViewById(R.id.list_element_exercise_calories);
		int calorieExpenditure = ((int) (.0175 * ((Float) exercises.get(position).get("METS"))
				* (((float)(userWeight + gearWeight)) / 2.2f))) * 60;
		cal.setText(calorieExpenditure + " cal/hr");
		
		return view;

	}

}
