package model;

import java.io.Serializable;

/** 
 * Classe FoodValues, implementa Food e Serializable
 */
public class FoodValues implements Food, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int KCAL_CARBS = 4;
	private static final int KCAL_FATS = 9;
	private static final int KCAL_PROTS = 4;

	
	private double carbs;
	private double fats;
	private double prots;
	private double fibers;
	
	private double carbsKCals;
	private double fatsKCals;
	private double protsKCals;
	
	private double totKcals;
	
	private String name;
	
	/** 
	 * Costruttore FoodValues
	 * 
	 * @param String
	 *     il nome dell'alimento
	 * 
	 * @param double
	 *     la quantità di carboidrati per 100 g 
	 * 
	 * @param double
     *     la quantità di grassi per 100 g
     *     
     * @param double
     *     la quantità di proteine per 100 g
     * 
     * @param double
     *     la quantità di fibre per 100 g
	 */
	public FoodValues (String name, double carbs, double fats, double prots, double fibers){
		
		this.name = name;
		this.carbs = carbs;
		this.fats = fats;
		this.prots = prots;
		this.fibers = fibers;
		
		this.carbsKCals = this.carbs * KCAL_CARBS;
		this.fatsKCals = this.fats * KCAL_FATS;
		this.protsKCals = this.prots * KCAL_PROTS;
		
		this.totKcals = this.processTotKcals();
	}
	
	public double processTotKcals(){
		return getCarbsKCals() + getFatsKCals() + getProtsKCals();
	}
	
	public double getCarbs() {
		return carbs;
	}

	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}

	public double getFats() {
		return fats;
	}

	public void setFats(double fats) {
		this.fats = fats;
	}

	public double getProts() {
		return prots;
	}

	public void setProts(double prots) {
		this.prots = prots;
	}

	public double getFibers() {
		return fibers;
	}

	public void setFibers(double fibers) {
		this.fibers = fibers;
	}

	public double getCarbsKCals() {
		return carbsKCals;
	}

	public void setCarbsKCals(double carbsKCals) {
		this.carbsKCals = carbsKCals;
	}

	public double getFatsKCals() {
		return fatsKCals;
	}

	public void setFatsKCals(double fatsKCals) {
		this.fatsKCals = fatsKCals;
	}

	public double getProtsKCals() {
		return protsKCals;
	}

	public void setProtsKCals(double protsKCals) {
		this.protsKCals = protsKCals;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getTotKcals(){
		return this.totKcals;
	}
	
}
