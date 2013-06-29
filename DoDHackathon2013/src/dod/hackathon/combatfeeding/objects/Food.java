package dod.hackathon.combatfeeding.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class Food extends GenericJson {
		
		@Key("ITEM")
		private String name;
		
		@Key("MENU")
		private String menu;
		
		@Key("ITEMTYPE")
		private String itemType;
		
		@Key("_id")
		private String id;
		
		@Key("RATION")
		private String ration;
		
		@Key("CARBOHYDRATES_G")
		private float carbs;
		
		@Key("PROTEIN_G")
		private float proteins;
		
		@Key("TOTALFAT_G")
		private float fats;
		
		@Key("CALORIES")
		private float calories;

}
	
	/*private boolean wasFetched = false;
	private FoodEntity food;

	// Get whether the food has been successfully fetched.
	public boolean wasFetched() {
		return wasFetched;
	}
	
	public Food(Client kinveyClient, String id) {
		
		food = new FoodEntity();
	    
	    AsyncAppData<FoodEntity> myFoods = kinveyClient.appData("foods", FoodEntity.class);
	    myFoods.getEntity(id, new KinveyClientCallback<FoodEntity>() {
	    	
	    	@Override
	    	public void onSuccess(FoodEntity result) {
	    		wasFetched = true;
	    	}
	    	
	    	@Override
	    	public void onFailure(Throwable error) {
	    		wasFetched = false;
	    	}
	    	
	    });
	}*/
