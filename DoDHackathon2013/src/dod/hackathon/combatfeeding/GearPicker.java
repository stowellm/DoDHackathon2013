package dod.hackathon.combatfeeding;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.callback.KinveyListCallback;

import dod.hackathon.combatfeeding.objects.Gear;
import dod.hackathon.combatfeeding.objects.GearAdapter;

public class GearPicker extends Activity {

	private ListView lv;
	private GearAdapter gearAdapter;
	private ArrayList<Gear> gear;
	float weightInLbs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picker_gear);

		
		new FetchFromKinveyTask().execute();
		
		lv = (ListView) findViewById(R.id.picker_gear_listview);
		lv.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
			
		});
		
	}

	private class FetchFromKinveyTask extends AsyncTask<Void, Integer, Void> {

		ProgressDialog dia;
		
		@Override
		protected void onPreExecute() {

			dia = new ProgressDialog(GearPicker.this);
			dia.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dia.setMessage("Please wait while fetching from the database");
			dia.setCancelable(false);
			dia.show();

		}

		@Override
		protected Void doInBackground(Void... voids) {
			
			gear = new ArrayList<Gear>();

			AsyncAppData<Gear> myevents = MainActivity.mKinveyClient.appData(
					"GearWeight", Gear.class);
			myevents.get(new KinveyListCallback<Gear>() {
				@Override
				public void onSuccess(Gear[] results) {
					gear = new ArrayList<Gear>(Arrays.asList(results));
				}

				@Override
				public void onFailure(Throwable error) {
					Log.e("tag", "failed to fetch all", error);
				}
			});
			
			while(gear.size() == 0) {
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

			gearAdapter = new GearAdapter(GearPicker.this,
					R.layout.list_element_gear, gear);
			
			lv.setAdapter(gearAdapter);
			lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		}

	}

}
