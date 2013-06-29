package dod.hackathon.combatfeeding.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class Exercise extends GenericJson {
	
	@Key("CLASS")
	private String exerciseClass;
	
	@Key("DESCRIPTION")
	private String menu;
	
	@Key("EXERCISES")
	private String exercises;
	
	@Key("_id")
	private String id;
	
	@Key("INTENSITY")
	private String intensity;
	
	@Key("METS")
	private float mets;

}
