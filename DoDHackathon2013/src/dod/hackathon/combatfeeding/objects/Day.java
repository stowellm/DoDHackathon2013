package dod.hackathon.combatfeeding.objects;

import java.util.ArrayList;

public class Day {
	
	public Day() {
		exerciseLog = new ArrayList<Exercise>();
		foodLog = new ArrayList<Food>();
	}
	
	/* Basic Nutrition Fields */
	private int calorieIntake;
	private int calorieRequired;
	private int proteinIntake;
	private int proteinRequired;
	private int fatIntake;
	private int fatRequired;
	private int carbIntake;
	private int carbRequired;
	
	/* Logs for Excerise and foods */
	private ArrayList<Exercise> exerciseLog;
	private ArrayList<Food> foodLog;
	
	/* Getters and Setters for Nutrition Fields */
	public int getCalorieIntake() {
		return calorieIntake;
	}
	
	public void setCalorieIntake(int calorieIntake) {
		this.calorieIntake = calorieIntake;
	}
	
	public int getCalorieRequired() {
		return calorieRequired;
	}
	
	public int getProteinIntake() {
		return proteinIntake;
	}
	
	public void setProteinIntake(int proteinIntake) {
		this.proteinIntake = proteinIntake;
	}
	
	public int getProteinRequired() {
		return proteinRequired;
	}
	
	public void setProteinRequired(int proteinRequired) {
		this.proteinRequired = proteinRequired;
	}
	
	public int getFatIntake() {
		return fatIntake;
	}
	
	public void setFatIntake(int fatIntake) {
		this.fatIntake = fatIntake;
	}
	
	public int getFatRequired() {
		return fatRequired;
	}
	
	public void setFatRequired(int fatRequired) {
		this.fatRequired = fatRequired;
	}
	
	public int getCarbIntake() {
		return carbIntake;
	}
	
	public void setCarbIntake(int carbIntake) {
		this.carbIntake = carbIntake;
	}
	
	public int getCarbRequired() {
		return carbRequired;
	}
	
	public void setCarbRequired(int carbRequired) {
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
	public void calcRequiredValues(boolean isMale, int age, int height, float weight, int weightDir) {
		//Harris-Benedict Formula for calculating required calories
		if(isMale) {
			calorieRequired = (int) (66 + ( 6.23 * weight ) + ( 12.7 * height ) - ( 6.8 * age ));
		} else {
			calorieRequired = (int) (655 + ( 4.35 * weight ) + ( 4.7 * height ) - ( 4.7 * age ));
		}
		if(weightDir == 0) { //percentages for maintaining weight
			fatRequired = (int) (calorieRequired * 0.3);
			proteinRequired = (int) (calorieRequired * 0.2);
			carbRequired = (int) (calorieRequired * 0.5);
		} else if(weightDir < 0) { //percentages for gaining weight
			fatRequired = (int) (calorieRequired * 0.25);
			proteinRequired = (int) (calorieRequired * 0.30);
			carbRequired = (int) (calorieRequired * 0.45);
		} else { //percentages for losing weight
			fatRequired = (int) (calorieRequired * 0.3);
			proteinRequired = (int) (calorieRequired * 0.2);
			carbRequired = (int) (calorieRequired * 0.5);
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
