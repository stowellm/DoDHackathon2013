package dod.hackathon.combatfeeding.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class Gear extends GenericJson {
	
	@Key("CLASS")
	private String infantryClass;
	
	@Key("WEAPON")
	private String weapon;
	
	@Key("WEAPON LOADED (LBS)")
	private float weightLoaded;
	
	@Key("_id")
	private String id;
	
	@Key("WEAPON UNLOADED (LBS)")
	private float weightUnloaded;

}
