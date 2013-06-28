package dod.hackathon.combatfeeding;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.User;

public class MainActivity extends Activity {
	
	public RestAPI rapi;
	final Client mKinveyClient;
	final String TAG = "COMBATFEED";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		rapi = new RestAPI();
		
		//Create the Kinvey client
		mKinveyClient = new Client.Builder("kid_VVIhP46N0f", "6601c902d5924ee29edcdbf07e445850",
			    							this.getApplicationContext()).build();
		//Create the implicit Kinvey user if none exists
		mKinveyClient.user().login(new KinveyUserCallback() {
		    @Override
		    public void onFailure(Throwable error) {
		        Log.e(TAG, "Login Failure", error);
		    }
		    @Override
		    public void onSuccess(User result) {
		        Log.i(TAG,"Logged in successfully as " + result.getId());
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
