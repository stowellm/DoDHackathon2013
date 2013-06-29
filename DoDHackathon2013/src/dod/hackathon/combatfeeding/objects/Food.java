package dod.hackathon.combatfeeding.objects;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.java.core.KinveyClientCallback;

import dod.hackathon.combatfeeding.objects.entities.FoodEntity;

public class Food {
	private boolean wasFetched = false;
	private FoodEntity food;

	// Get whether the food has been successfully fetched.
	public boolean wasFetched() {
		return wasFetched;
	}
	
	public Food(Client kinveyClient) {
		
	    food = new FoodEntity();
	    
	    AsyncAppData<FoodEntity> myFoods = kinveyClient.appData("events", FoodEntity.class);
	    myFoods.getEntity(eventId, new KinveyClientCallback<FoodEntity>() {
	    	
	    	@Override
	    	public void onSuccess(FoodEntity result) {
	    		wasFetched = true;
	    	}
	    	
	    	@Override
	    	public void onFailure(Throwable error) {
	    		wasFetched = false;
	    	}
	    	
	    });
	}

}
