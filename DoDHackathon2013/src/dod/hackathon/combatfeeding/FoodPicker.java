package dod.hackathon.combatfeeding;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Spinner;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.callback.KinveyListCallback;

import dod.hackathon.combatfeeding.objects.Food;
import dod.hackathon.combatfeeding.objects.FoodAdapter;
import dod.hackathon.combatfeeding.objects.FoodAdapter.SortType;

public class FoodPicker extends Activity {

	private ListView lv;
	private SearchView sv;
	private FoodAdapter foodAdapter;
	private ArrayList<Food> foods;
	private Spinner spin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picker_food);

		new FetchFromKinveyTask().execute();
		
		lv = (ListView) findViewById(R.id.picker_food_listview);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long l) {
				Food f = foodAdapter.getItem(pos);
				Intent ret = new Intent();
				ret.putExtra("food", f);
				setResult(RESULT_OK, ret);
				finish();
			}
		});

		sv = (SearchView) findViewById(R.id.picker_view_search);
		sv.setOnQueryTextListener(new OnQueryTextListener() {
			@Override
			public boolean onQueryTextChange(String newText) {
				foodAdapter.getFilter().filter(newText);
				return false;
			}

			@Override
			public boolean onQueryTextSubmit(String arg0) {
				return false;
			}
		});

		String choices[] = new String[2];
		choices[0] = "by food";
		choices[1] = "by menu";
		spin = (Spinner) findViewById(R.id.picker_view_spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(FoodPicker.this, android.R.layout.simple_spinner_item, choices);
		spin.setAdapter(adapter);
		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				SortType sortType;
				if (position == 0) {
					sortType = SortType.ITEM_NAME;
					sv.setInputType(InputType.TYPE_CLASS_TEXT);
				} else {
					sortType = SortType.ITEM_MENU;
					sv.setInputType(InputType.TYPE_CLASS_PHONE);
				}
				if (foodAdapter != null) foodAdapter.setSortType(sortType);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
	}

	private class FetchFromKinveyTask extends AsyncTask<Void, Integer, Void> {

		ProgressDialog dia;
		
		@Override
		protected void onPreExecute() {

			dia = new ProgressDialog(FoodPicker.this);
			dia.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dia.setMessage("Please wait while fetching from the database");
			dia.setCancelable(false);
			dia.show();

		}

		@Override
		protected Void doInBackground(Void... voids) {
			
			foods = new ArrayList<Food>();

			AsyncAppData<Food> myevents = MainActivity.mKinveyClient.appData(
					"mre2013", Food.class);
			myevents.get(new KinveyListCallback<Food>() {
				@Override
				public void onSuccess(Food[] results) {
					foods = new ArrayList<Food>(Arrays.asList(results));
				}

				@Override
				public void onFailure(Throwable error) {
					Log.e("tag", "failed to fetch all", error);
				}
			});
			
			while(foods.size() == 0) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			publishProgress(100);
			return null;

		}

		@Override
		protected void onPostExecute(Void voids) {
			dia.setMessage("Done");
			dia.cancel();

			foodAdapter = new FoodAdapter(FoodPicker.this,
					R.layout.list_element_food, foods);
			
			lv.setAdapter(foodAdapter);
		}

	}

}
