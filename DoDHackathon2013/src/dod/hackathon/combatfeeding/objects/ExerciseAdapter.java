package dod.hackathon.combatfeeding.objects;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {

	private ArrayList<Exercise> exercises;
	private Context context;
	private int layoutId;

	public ExerciseAdapter(Context context, int textViewResourceId,
			ArrayList<Exercise> exercises) {
		super(context, textViewResourceId);

		this.context = context;
		this.exercises = exercises;
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

		// TODO

		return view;

	}

}
