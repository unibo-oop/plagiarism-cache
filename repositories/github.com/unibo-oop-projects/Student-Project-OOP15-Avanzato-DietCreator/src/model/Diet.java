package model;

import java.io.Serializable;
import java.util.List;

/**
 * Classe che rappresenta una dieta, contenente un insieme di pasti, un obiettivo, peso iniziale
 * e peso finale
 * 
 */

public class Diet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private DCList<Meal> list;
	private String target;
	private double initialWeight;
	private double finalWeight;
	
	//values
	private double totCarbs;
	private double totFats;
	private double totProts;
	private double totFibers;
	private double totKcals;
	
	/**
	 * Costruttore della classe dieta
	 * 
	 * @param name
	 *     il nome della dieta
	 * 
	 * @param target
	 *     l'obiettivo della dieta
	 *     
	 * @param initialWeight
	 *     il peso all'inizio della dieta
	 * 
	 */
	public Diet(String name, String target, double initialWeight){
		this.name = name;
		this.target = target;
		this.initialWeight = initialWeight;
		this.list = new DCList<>();
		this.totCarbs = 0;
		this.totFats = 0;
		this.totFibers = 0;
		this.totProts = 0;
		this.totKcals = 0;
	}
	
	/**
	 * Metodo getter di un pasto specifico di una dieta
	 * 
	 * @param number
	 *     il parametro indica il numero del pasto 
	 * 
	 * @return
	 *     restituisce il pasto
	 * 
	 */
	public Meal getMeal(int number){
		return this.list.get(number-1);
	}
	
	/**
	 * Metodo getter del nome della dieta
	 * 
	 * @return
	 *     restituisce il nome della dieta
	 * 
	 */
	public String getName(){
		return this.name;
	}
	
	/**
     * Metodo setter del nome della dieta
     * 
     * @param name
     *     il nuovo nome della dieta
     * 
     */
	public void setName(String name){
		this.name = name;
	}
	
	/**
     * Metodo getter della lista di pasti di una dieta
     * 
     * @return
     *     restituisce la lista di pasti
     * 
     */
	public List<Meal> getMealList(){
		return this.list;
	}
	
	/**
     * Metodo setter della lista di pasti di una dieta.
     * Sostituisce la vecchia lista con una nuova.
     * Ricalcolo dei valori della dieta.
     * 
     * @param list
     *     la nuova lista di pasti
     * 
     */
	public void setMealList(DCList<Meal> list){
		this.list = list;
		this.processValues();
	}
	
	/**
     * Metodo che permette di aggiungere un pasto alla lista di pasti.
     * 
     * @param meal
     *     il pasto da inserire
     * 
     */
	public void addMeal(Meal meal){
		this.list.add(meal);
	}
	
	/**
     * Metodo getter dell'obiettivo di una dieta
     * 
     * @return
     *     restituisce l'obiettivo di una dieta
     * 
     */
	public String getTarget(){
		return this.target;
	}
	
	/**
     * Metodo getter del peso dell'utente all'inizio della dieta
     * 
     * @return
     *     restituisce il peso dell'utente all'inizio della dieta
     * 
     */
	public double getInitialWeight(){
		return this.initialWeight;
	}
	
	/**
     * Metodo getter del peso dell'utente alla fine della dieta
     * 
     * @return
     *     restituisce il peso dell'utente alla fine della dieta
     * 
     */
	public double getFinalWeight(){
		return this.finalWeight;
	}
	
	/**
     * Metodo setter del peso dell'utente alla fine della dieta
     * 
     * @param fW
     *     il peso da settare come peso dell'utente alla fine della dieta
     * 
     */
	public void setFinalWeight(double fW){
		this.finalWeight = fW;
	}
	
	/**
     * Metodo getter delle calorie totali di una dieta
     * 
     * @return
     *     restituisce le calorie totali di una dieta
     * 
     */
	public double getTotKcals(){
		return this.totKcals;
	}
	
	/**
     * Metodo getter dei carboidrati totali di una dieta
     * 
     * @return
     *     restituisce dei carboidrati totali di una dieta
     * 
     */
	public double getTotCarbs(){
		return this.totCarbs;
	}
	
	/**
     * Metodo getter dei grassi totali di una dieta
     * 
     * @return
     *     restituisce dei grassi totali di una dieta
     * 
     */
	public double getTotFats(){
		return this.totFats;
	}
	
	/**
     * Metodo getter delle proteine totali di una dieta
     * 
     * @return
     *     restituisce le proteine totali di una dieta
     * 
     */
	public double getTotProts(){
		return this.totProts;
	}
	
	/**
     * Metodo getter delle fibre totali di una dieta
     * 
     * @return
     *     restituisce le fibre totali di una dieta
     * 
     */
	public double getTotFibers(){
		return this.totFibers;
	}
	
	/**
     * Metodo privato che esegue un calcolo dei valori totali di una dieta.
     * I valori sono carboidrati, grassi, proteine, fibre e kcal.
     * 
     */
	private void processValues(){
		this.totCarbs = 0;
		this.totFats = 0;
		this.totProts = 0;
		this.totFibers = 0;
		this.totKcals = 0;
		
		this.list.forEach(m -> {
			this.totCarbs += m.getTotCarbs();
			this.totFats += m.getTotFats();
			this.totProts += m.getTotProts();
			this.totFibers += m.getTotFibers();
			this.totKcals += m.getTotKcals();
		});
	}
}
