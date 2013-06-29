package dod.hackathon.combatfeeding;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.callback.KinveyListCallback;

import dod.hackathon.combatfeeding.objects.Exercise;
import dod.hackathon.combatfeeding.objects.ExerciseAdapter;

public class ExercisePicker extends Activity {

	private ListView listCombat, listNonCombat;
	
	private int gearWeight;

	private ExerciseAdapter combatAdapter, nonCombatAdapter;
	private ArrayList<Exercise> exercises, combatExercises, nonCombatExercises;

	private Button ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picker_exercise);
		
		Bundle extras = getIntent().getExtras();
		gearWeight = extras.getInt("gear_weight");

		listCombat = (ListView) findViewById(R.id.picker_exercise_combat_list);
		listNonCombat = (ListView) findViewById(R.id.picker_exercise_noncombat_list);

		ok = (Button) findViewById(R.id.picker_exercise_ok);
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				ArrayList<String> exercises = new ArrayList<String>();
				ArrayList<Integer> times = new ArrayList<Integer>();

				for (int count = 0; count < combatAdapter.placeholder.size(); count++) {
					Integer i = combatAdapter.placeholder.get(count);
					if (i != 0) {
						times.add(getTime(combatAdapter.placeholder.get(count)));
						exercises.add((String) combatAdapter.exercises.get(count)
								.get("EXERCISES"));
					}
				}

				for (int count = 0; count < nonCombatAdapter.placeholder.size(); count++) {
					Integer i = nonCombatAdapter.placeholder.get(count);
					if (i != 0) {
						times.add(getTime(nonCombatAdapter.placeholder.get(count)));
						exercises.add((String) nonCombatAdapter.exercises.get(count)
								.get("EXERCISES"));
					}
				}

				String[] exercisesReturn = new String[exercises.size()];
				int[] timesReturn = new int[times.size()];
				for (int j = 0; j < exercises.size(); j++) {
					exercisesReturn[j] = exercises.get(j);
					timesReturn[j] = times.get(j);
				}

				Intent iReturn = new Intent();
				iReturn.putExtra("exercise_names", exercisesReturn);
				iReturn.putExtra("exercise_times", timesReturn);
				
				for (int i = 0; i < timesReturn.length; i++) Log.w("tag", "Time: " + timesReturn[i]);

				setResult(RESULT_OK, iReturn);
				finish();

			}
		});

		new FetchFromKinveyTask().execute();
	}

	private int getTime(int position) {

		switch (position) {
		case 0:
			return -1;
		case 1:
			return 0;
		case 2:
			return 100;
		case 3:
			return 200;
		case 4:
			return 300;
		case 5:
			return 400;
		case 6:
			return 500;
		case 7:
			return 600;
		case 8:
			return 700;
		case 9:
			return 800;
		case 10:
			return 900;
		case 11:
			return 1000;
		case 12:
			return 1100;
		case 13:
			return 1200;
		case 14:
			return 1300;
		case 15:
			return 1400;
		case 16:
			return 1500;
		case 17:
			return 1600;
		case 18:
			return 1700;
		case 19:
			return 1800;
		case 20:
			return 1900;
		case 21:
			return 2000;
		case 22:
			return 2100;
		case 23:
			return 2200;
		case 24:
			return 2300;
		}

		return 0;
	}

	private class FetchFromKinveyTask extends AsyncTask<Void, Integer, Void> {

		ProgressDialog dia;

		@Override
		protected void onPreExecute() {

			dia = new ProgressDialog(ExercisePicker.this);
			dia.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dia.setMessage("Please wait while fetching from the database");
			dia.setCancelable(false);
			dia.show();

		}

		@Override
		protected Void doInBackground(Void... voids) {

			exercises = new ArrayList<Exercise>();

			AsyncAppData<Exercise> myevents = MainActivity.mKinveyClient
					.appData("ENERGYEXPENDITURE", Exercise.class);
			myevents.get(new KinveyListCallback<Exercise>() {
				@Override
				public void onSuccess(Exercise[] results) {
					exercises = new ArrayList<Exercise>(Arrays.asList(results));
				}

				@Override
				public void onFailure(Throwable error) {
					Log.e("tag", "failed to fetch all", error);
				}
			});

			while (exercises.size() == 0) {
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

			combatExercises = new ArrayList<Exercise>();
			nonCombatExercises = new ArrayList<Exercise>();

			for (Exercise e : exercises) {
				if (((String) e.get("CLASS")).equals("COMBAT")) {
					combatExercises.add(e);
				} else {
					nonCombatExercises.add(e);
				}
			}
			
			SharedPreferences mPrefs = getSharedPreferences("dod_hackathon", 0);
			float userWeight = mPrefs.getFloat("my_weight", -1.0f);

			combatAdapter = new ExerciseAdapter(ExercisePicker.this,
					R.layout.list_element_exercise, combatExercises, gearWeight, userWeight);

			listCombat.setAdapter(combatAdapter);

			nonCombatAdapter = new ExerciseAdapter(ExercisePicker.this,
					R.layout.list_element_exercise, nonCombatExercises, gearWeight, userWeight);

			listNonCombat.setAdapter(nonCombatAdapter);
		}

	}

}
