package dod.hackathon.combatfeeding;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import dod.hackathon.combatfeeding.api.*;

public class MainActivity extends Activity {
	
	public RestAPI rapi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		rapi = new RestAPI();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
