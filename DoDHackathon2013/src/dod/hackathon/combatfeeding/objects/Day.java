package dod.hackathon.combatfeeding.objects;

import java.util.ArrayList;

public class Day {
	
	public Day() {
		exerciseLog = new ArrayList<Exercise>();
		foodLog = new ArrayList<Food>();
	}
	
	/* Basic Nutrition Fields */
	private double calorieIntake;
	private double calorieRequired;
	private double proteinIntake;
	private double proteinRequired;
	private double fatIntake;
	private double fatRequired;
	private double carbIntake;
	private double carbRequired;
	
	/* Logs for Excerise and foods */
	private ArrayList<Exercise> exerciseLog;
	private ArrayList<Food> foodLog;
	
	/* Getters and Setters for Nutrition Fields */
	public double getCalorieIntake() {
		return calorieIntake;
	}
	
	public void setCalorieIntake(double calorieIntake) {
		this.calorieIntake = calorieIntake;
	}
	
	public double getCalorieRequired() {
		return calorieRequired;
	}
	
	public double getProteinIntake() {
		return proteinIntake;
	}
	
	public void setProteinIntake(double proteinIntake) {
		this.proteinIntake = proteinIntake;
	}
	
	public double getProteinRequired() {
		return proteinRequired;
	}
	
	public void setProteinRequired(double proteinRequired) {
		this.proteinRequired = proteinRequired;
	}
	
	public double getFatIntake() {
		return fatIntake;
	}
	
	public void setFatIntake(double fatIntake) {
		this.fatIntake = fatIntake;
	}
	
	public double getFatRequired() {
		return fatRequired;
	}
	
	public void setFatRequired(double fatRequired) {
		this.fatRequired = fatRequired;
	}
	
	public double getCarbIntake() {
		return carbIntake;
	}
	
	public void setCarbIntake(double carbIntake) {
		this.carbIntake = carbIntake;
	}
	
	public double getCarbRequired() {
		return carbRequired;
	}
	
	public void setCarbRequired(double carbRequired) {
		this.carbRequired = carbRequired;
	}
	
	/* Getters for exercise and food. */
	public ArrayList<Exercise> getExerciseLog() {
		return exerciseLog;
	}

	public ArrayList<Food> getfoodLog() {
		return foodLog;
	}
	
	/* Function to calculate the required values */
	public void calcRequiredValues(boolean isMale, double age, double height, float weight, double weightDir) {
		//Harris-Benedict Formula for calculating required calories
		if(isMale) {
			calorieRequired = (double) (66 + ( 6.23 * weight ) + ( 12.7 * height ) - ( 6.8 * age ));
		} else {
			calorieRequired = (double) (655 + ( 4.35 * weight ) + ( 4.7 * height ) - ( 4.7 * age ));
		}
		if(weightDir == 0) { //percentages for madoubleaining weight
			fatRequired = (double) (calorieRequired * 0.3) / 9;
			proteinRequired = (double) (calorieRequired * 0.2) / 4;
			carbRequired = (double) (calorieRequired * 0.5) / 4;
		} else if(weightDir < 0) { //percentages for gaining weight
			fatRequired = (double) (calorieRequired * 0.25) / 9;
			proteinRequired = (double) (calorieRequired * 0.30) / 4;
			carbRequired = (double) (calorieRequired * 0.45) / 4;
		} else { //percentages for losing weight
			fatRequired = (double) (calorieRequired * 0.3) / 9;
			proteinRequired = (double) (calorieRequired * 0.2) / 4;
			carbRequired = (double) (calorieRequired * 0.5) / 4;
			calorieRequired -= 500;
		}
	}

	
	/* Functions relating to managing food/exercise vectors. */
	
	// Add a new exercise to exercise log.
	public void addExercise(Exercise exercise) {
		exerciseLog.add(exercise);
	}
	
	// Add a new food to food log.
	public void addFood(Food food) {
		foodLog.add(food);
		
		//reset counts to 0
		calorieIntake = 0;
		fatIntake = 0;
		carbIntake = 0;
		proteinIntake = 0;
		
		//iterate over foods, sum their counts
		for(Food f : foodLog) {
			calorieIntake += Double.parseDouble((String)f.get("CALORIES"));
			fatIntake += Double.parseDouble((String) f.get("TOTALFAT_G"));
			carbIntake += Double.parseDouble((String) f.get("CARBOHYDRATES_G"));
			proteinIntake += Double.parseDouble((String) f.get("PROTEIN_G"));
		}
	}
	
	// Delete an exercise from exercise log.
	public void deleteExercise(Exercise exercise) {
		exerciseLog.remove(exercise);
	}
	
	// Delete a food from the food log.
	public void deleteFood(Food food) {
		foodLog.remove(food);
	}
	
}
