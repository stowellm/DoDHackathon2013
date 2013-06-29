package dod.hackathon.combatfeeding;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class TimelineActivity extends Activity {

	private TextView t0000, t0100, t0200, t0300, t0400, t0500, t0600,
		t0700, t0800, t0900, t1000, t1100, t1200, t1300, t1400, t1500,
		t1600, t1700, t1800, t1900, t2000, t2100, t2200, t2300;
	
	private String[] exerciseNames;
	private int[] exerciseTimes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		
		Bundle extras = getIntent().getExtras();
		exerciseNames = extras.getStringArray("exercise_names");
		exerciseTimes = extras.getIntArray("exercise_times");
		
		t0000 = (TextView) findViewById(R.id.timeslot_000);
		t0100 = (TextView) findViewById(R.id.timeslot_100);
		t0200 = (TextView) findViewById(R.id.timeslot_200);
		t0300 = (TextView) findViewById(R.id.timeslot_300);
		t0400 = (TextView) findViewById(R.id.timeslot_400);
		t0500 = (TextView) findViewById(R.id.timeslot_500);
		t0600 = (TextView) findViewById(R.id.timeslot_600);
		t0700 = (TextView) findViewById(R.id.timeslot_700);
		t0800 = (TextView) findViewById(R.id.timeslot_800);
		t0900 = (TextView) findViewById(R.id.timeslot_900);
		t1000 = (TextView) findViewById(R.id.timeslot_1000);
		t1100 = (TextView) findViewById(R.id.timeslot_1100);
		t1200 = (TextView) findViewById(R.id.timeslot_1200);
		t1300 = (TextView) findViewById(R.id.timeslot_1300);
		t1400 = (TextView) findViewById(R.id.timeslot_1400);
		t1500 = (TextView) findViewById(R.id.timeslot_1500);
		t1600 = (TextView) findViewById(R.id.timeslot_1600);
		t1700 = (TextView) findViewById(R.id.timeslot_1700);
		t1800 = (TextView) findViewById(R.id.timeslot_1800);
		t1900 = (TextView) findViewById(R.id.timeslot_1900);
		t2000 = (TextView) findViewById(R.id.timeslot_2000);
		t2100 = (TextView) findViewById(R.id.timeslot_2100);
		t2200 = (TextView) findViewById(R.id.timeslot_2200);
		t2300 = (TextView) findViewById(R.id.timeslot_2300);
		
		parseActivitiesAndTimes();
		
	}
	
	private void parseActivitiesAndTimes() {
		for (int i = 0; i < exerciseTimes.length; i++) {
			int time = exerciseTimes[i];
			String name = exerciseNames[i];
			
			switch (time) {
			case 0:
				t0000.setText(name); t0000.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 100:
				t0100.setText(name); t0100.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 200:
				t0200.setText(name); t0200.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 300:
				t0300.setText(name); t0300.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 400:
				t0400.setText(name); t0400.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 500:
				t0500.setText(name); t0500.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 600:
				t0600.setText(name); t0600.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 700:
				t0700.setText(name); t0700.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 800:
				t0800.setText(name); t0800.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 900:
				t0900.setText(name); t0900.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 1000:
				t1000.setText(name); t1000.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 1100:
				t1100.setText(name); t1100.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 1200:
				t1200.setText(name); t1200.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 1300:
				t1300.setText(name); t1300.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 1400:
				t1400.setText(name); t1400.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 1500:
				t1500.setText(name); t1500.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 1600:
				t1600.setText(name); t1600.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 1700:
				t1700.setText(name); t1700.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 1800:
				t1800.setText(name); t1800.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 1900:
				t1900.setText(name); t1900.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 2000:
				t2000.setText(name); t2000.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 2100:
				t2100.setText(name); t2100.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 2200:
				t2200.setText(name); t2200.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			case 2300:
				t2300.setText(name); t2300.setTextColor(getResources().getColor(R.color.greenend));
				continue;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}