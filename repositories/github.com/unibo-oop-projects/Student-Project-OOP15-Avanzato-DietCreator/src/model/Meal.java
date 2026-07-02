package model;

import java.io.Serializable;
import java.util.List;

/**
 * Classe Meal, rappresenta un pasto
 */
public class Meal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DCList<FoodOnDiet> list;
	private int number;
	private double totKcals;
	private double totCarbs;
	private double totFats;
	private double totProts;
	private double totFibers;

	/**
     * Costruttore di Meal
     * 
     * @param number
     *      il numero del pasto all'interno di una dieta
     * 
     * @param list
     *      la lista di alimenti inseriti nel pasto
     */
	public Meal(int number, DCList<FoodOnDiet> list){
		this.number = number;
		this.list = new DCList<>();
		this.list.addAll(list);
		this.processTotCarbs();
		this.processTotFats();
		this.processTotProts();
		this.processTotFibers();
		this.processTotKCals();
	}
	
	/**
     * getter della lista di alimenti del pasto
     * 
     * @return
     *      la lista di alimenti del pasto
     */
	public List<FoodOnDiet> getFList(){
		return this.list;
	}
	
	/**
     * getter del numero del pasto all'interno di una dieta
     * 
     * @return
     *      il numero di pasto
     */
	public int getNumber(){
		return this.number;
	}
	
	/**
     * getter delle calorie totali del pasto
     * 
     * @return le calorie totali del pasto
     */
	public double getTotKcals(){
		return this.totKcals;
	}
	
	/**
     * getter dei carboidrati totali del pasto
     * 
     * @return i carboidrati totali del pasto
     */
	public double getTotCarbs(){
		return this.totCarbs;
	}
	
	/**
     * getter dei grassi totali del pasto
     * 
     * @return i grassi totali del pasto
     */
	public double getTotFats(){
		return this.totFats;
	}
	
	/**
     * getter delle proteine totali del pasto
     * 
     * @return le proteine totali del pasto
     */
	public double getTotProts(){
		return this.totProts;
	}
	
	/**
     * getter delle fibre totali del pasto
     * 
     * @return le fibre totali del pasto
     */
	public double getTotFibers(){
		return this.totFibers;
	}
	
	/**
     * calcola le calorie totali del pasto
     * 
     */
	private void processTotKCals(){
		this.totKcals = 0;
		this.list.stream().forEach(f -> this.totKcals += f.processTotKcals());
	}
	
	/**
     * calcola i carboidrati totali del pasto
     * 
     */
	private void processTotCarbs(){
		this.totCarbs = 0;
		this.list.stream().forEach(f -> this.totCarbs += f.getCarbs());
	}
	
	/**
     * calcola i grassi totali del pasto
     * 
     */
	private void processTotFats(){
		this.totFats = 0;
		this.list.stream().forEach(f -> this.totFats += f.getFats());
	}
	
	/**
     * calcola le proteine totali del pasto
     * 
     */
	private void processTotProts(){
		this.totProts = 0;
		this.list.stream().forEach(f -> this.totProts += f.getProts());
	}
	
	/**
     * calcola le fibre totali del pasto
     * 
     */
	private void processTotFibers(){
		this.totFibers = 0;
		this.list.stream().forEach(f -> this.totFibers += f.getFibers());
	}
	
}
