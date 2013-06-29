package dod.hackathon.combatfeeding;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.callback.KinveyListCallback;

import dod.hackathon.combatfeeding.objects.Exercise;
import dod.hackathon.combatfeeding.objects.ExerciseAdapter;

public class ExercisePicker extends Activity {
	
	private ListView listCombat, listNonCombat;
	
	private ExerciseAdapter combatAdapter, nonCombatAdapter;
	private ArrayList<Exercise> exercises, combatExercises, nonCombatExercises;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picker_exercise);
		
		listCombat = (ListView) findViewById(R.id.picker_exercise_combat_list);
		listNonCombat = (ListView) findViewById(R.id.picker_exercise_noncombat_list);
		
		new FetchFromKinveyTask().execute();
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

			AsyncAppData<Exercise> myevents = MainActivity.mKinveyClient.appData(
					"ENERGYEXPENDITURE", Exercise.class);
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
			
			while(exercises.size() == 0) {
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
			
			combatAdapter = new ExerciseAdapter(ExercisePicker.this,
					R.layout.list_element_exercise, combatExercises);
			
			listCombat.setAdapter(combatAdapter);
			
			nonCombatAdapter = new ExerciseAdapter(ExercisePicker.this,
					R.layout.list_element_exercise, nonCombatExercises);
			
			listNonCombat.setAdapter(nonCombatAdapter);
		}

	}

}
