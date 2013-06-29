package dod.hackathon.combatfeeding.objects;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import dod.hackathon.combatfeeding.R;

public class FoodAdapter extends ArrayAdapter<Food> implements Filterable {

	private ArrayList<Food> original;
	private ArrayList<Food> filtered;
	private SortType sortType;
	private Context context;
	private int layoutId;

	public enum SortType {
		ITEM_NAME, ITEM_MENU
	};

	@SuppressWarnings("unchecked")
	public FoodAdapter(Context context, int textViewResourceId,
			ArrayList<Food> original) {
		super(context, textViewResourceId);

		this.original = original;
		this.context = context;
		sortType = SortType.ITEM_NAME;
		layoutId = textViewResourceId;
		filtered = (ArrayList<Food>) original.clone();
	}

	@Override
	public Filter getFilter() {

		return new Filter() {

			@Override
			protected FilterResults performFiltering(CharSequence charSequence) {
				FilterResults results = new FilterResults();

				// If there's nothing to filter on, return the original data for
				// your list.
				if (charSequence == null || charSequence.length() == 0) {
					results.values = original;
					results.count = original.size();
				} else {
					// Reinitialize filtered results
					ArrayList<Food> newFiltered = new ArrayList<Food>();

					for (Food food : original) {
						// Sort by sort type
						switch (sortType) {
						case ITEM_NAME:
							if ((String) food.get("ITEM") != null) {
								if (((String) food.get("ITEM")).toLowerCase(
										Locale.US).contains(
										charSequence.toString().toLowerCase(
												Locale.US))) {
									newFiltered.add(food);
								}
							}
							break;

						case ITEM_MENU:
							if ((String) food.get("MENU") != null) {
								if (((String) food.get("MENU")).toLowerCase(
										Locale.US).contains(
										charSequence.toString().toLowerCase(
												Locale.US))) {
									newFiltered.add(food);
								}
							}
							break;

						default:
							Log.e("Adapter", "Bad stuff iz bad.");
							break;
						}

					}

					results.values = newFiltered;
					results.count = newFiltered.size();
				}

				return results;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence charSequence,
					FilterResults filterResults) {
				filtered = (ArrayList<Food>) filterResults.values;

				notifyDataSetChanged();
			}

		};
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	public ArrayList<Food> getFiltered() {
		return filtered;
	}

	// For this helper method, return based on filteredData
	public int getCount() {
		return filtered.size();
	}

	// This should return a data object, not an int
	public Food getItem(int position) {
		return filtered.get(position);
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

		TextView name = (TextView) view
				.findViewById(R.id.list_element_food_title);
		TextView cal = (TextView) view
				.findViewById(R.id.list_element_food_calories);
		TextView carb = (TextView) view
				.findViewById(R.id.list_element_food_carbs);
		TextView fat = (TextView) view.findViewById(R.id.list_element_food_fat);
		TextView prot = (TextView) view
				.findViewById(R.id.list_element_food_protein);

		name.setText("" + filtered.get(position).get("ITEM"));
		cal.setText("cal " + filtered.get(position).get("CALORIES"));
		carb.setText("carb " + filtered.get(position).get("CARBOHYDRATES_G"));
		fat.setText("fat " + filtered.get(position).get("TOTALFAT_G"));
		prot.setText("pro " + filtered.get(position).get("PROTEIN_G"));

		return view;

	}

}
