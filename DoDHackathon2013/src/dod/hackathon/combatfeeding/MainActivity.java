package dod.hackathon.combatfeeding;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.User;

public class MainActivity extends Activity {

	public static  Client mKinveyClient;
	private String TAG = "COMBATFEED";
	private SharedPreferences mPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPrefs = getSharedPreferences("dod_hackathon", 0);
		
		// Create the Kinvey client
		mKinveyClient = new Client.Builder("kid_VVIhP46N0f",
				"6601c902d5924ee29edcdbf07e445850",
				this.getApplicationContext()).build();
		// Create the implicit Kinvey user if none exists
		mKinveyClient.user().login(new KinveyUserCallback() {
			@Override
			public void onFailure(Throwable error) {
				Log.e(TAG, "Login Failure", error);
			}

			@Override
			public void onSuccess(User result) {
				Log.i(TAG, "Logged in successfully as " + result.getId());
				mKinveyClient.ping(new KinveyPingCallback() {
					public void onFailure(Throwable t) {
						Log.e(TAG, "Kinvey Ping Failed", t);
					}

					public void onSuccess(Boolean b) {
						Log.d(TAG, "Kinvey Ping Success");
					}
				});
			}
		});

		boolean firstSetup = mPrefs.getBoolean("first_setup", true);
		if (firstSetup) {
			Intent iProfile = new Intent(MainActivity.this, ProfileDialog.class);
			startActivity(iProfile);
		}
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		MainFragment fragment = new MainFragment();
		fragmentTransaction.add(R.id.mainholder, fragment);
		fragmentTransaction.commit();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		Fragment fragment = getFragmentManager().findFragmentById(R.id.mainholder);
	    fragment.onActivityResult(requestCode, resultCode, data);
	}

	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_profile:
			Intent iProfile = new Intent(MainActivity.this, ProfileDialog.class);
			startActivity(iProfile);
			return true;
		}
		return false;
	}*/

}
