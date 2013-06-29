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
