package dod.hackathon.combatfeeding.objects;

import java.util.Vector;

public class Day {
	
	/* Basic Nutrition Fields */
	public int calorieIntake;
	public int calorieRequired;
	public int proteinIntake;
	public int proteinRequired;
	public int fatIntake;
	public int fatRequired;
	public int carbIntake;
	public int carbRequired;
	
	/* Logs for Excerise and foods */
	Vector<Exercise> exerciseLog;
	Vector<Food> foodLog;
	
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
	
	public void setCalorieRequired(int calorieRequired) {
		this.calorieRequired = calorieRequired;
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
	public Vector<Exercise> getExerciseLog() {
		return exerciseLog;
	}

	public Vector<Food> getfoodLog() {
		return foodLog;
	}
	
	/* Functions relating to managing food/exercise vectors. */
	
	// Add a new exercise to exercise log.
	public boolean addExercise() {
		// TODO
		return true;
	}
	
	// Add a new food to food log.
	public boolean addFood() {
		// TODO
		return true;
	}
	
	// Delete an exercise from exercise log.
	public boolean deleteExercise() {
		// TODO
		return true;
	}
	
	// Delete a food from the food log.
	public boolean deleteFood() {
		// TODO
		return true;
	}
	
}
