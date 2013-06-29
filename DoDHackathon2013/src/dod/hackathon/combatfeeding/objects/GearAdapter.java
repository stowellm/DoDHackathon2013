package dod.hackathon.combatfeeding.objects;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import dod.hackathon.combatfeeding.R;

public class GearAdapter extends ArrayAdapter<Gear> {

	private ArrayList<Gear> gearList;
	private Context context;
	private int layoutId;

	public enum SortType {
		ITEM_NAME, ITEM_MENU
	};

	@SuppressWarnings("unchecked")
	public GearAdapter(Context context, int textViewResourceId,
			ArrayList<Gear> original) {
		super(context, textViewResourceId);

		gearList = original;
		this.context = context;
		layoutId = textViewResourceId;
	}

	// For this helper method, return based on filteredData
	public int getCount() {
		return gearList.size();
	}

	// This should return a data object, not an int
	public Gear getItem(int position) {
		return gearList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = convertView;
		if (convertView == null) {
			view = inflater.inflate(layoutId, parent, false);
		}

		CheckedTextView name = (CheckedTextView) view
				.findViewById(R.id.label_name);
		name.setText("" + gearList.get(position).get("WEAPON"));
		TextView mClass = (TextView) view
				.findViewById(R.id.label_class);
		mClass.setText("" + gearList.get(position).get("CLASS"));
		TextView weight = (TextView) view
				.findViewById(R.id.label_weight);
		weight.setText(gearList.get(position).get("WEAPON LOADED (LBS)") +" lbs");

		return view;

	}

}
