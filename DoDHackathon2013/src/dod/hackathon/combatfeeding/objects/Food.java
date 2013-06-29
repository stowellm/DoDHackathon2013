package dod.hackathon.combatfeeding.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class Food extends GenericJson {

	
		@Key("CARBOHYDRATES_G")
		public String carbs;
		
		@Key("MENU")
		public String menu;
		
		@Key("CALORIES")
		public String calories;
		
		@Key("_id")
		public String id;
		
		@Key("RATION")
		public String ration;
	
		@Key("ITEM")
		public String name;
				
		@Key("TOTALFAT_G")
		public String fats;

		@Key("PROTEIN_G")
		public String proteins;
		
		@Key("ITEMTYPE")
		public String itemType;		

}

