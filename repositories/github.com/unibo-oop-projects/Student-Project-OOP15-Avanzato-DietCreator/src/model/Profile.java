package model;

import java.io.Serializable;
import java.util.List;

/**
 * Classe che rappresenta un profilo utente
 */
public class Profile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double maintainingKCals;
	private double weight;
	private DCList<Diet> diets;
	private Somatotype somatotype;
	private String somatotypeName = "somatotype";
	
	/**
     * Costruttore di Profile
     * 
     * @param name
     *      il nome del profilo
     * 
     * @param kcals
     *      le calorie di manenimento del peso corporeo
     * 
     * @param somatotype
     *      il somatotipo del profilo
     * 
     * @param weight
     *      il peso attuale del profilo
     *  
     */
	public Profile(String name, double kcals, Somatotype somatotype, double weight){
		this.name = name;
		this.maintainingKCals = kcals;
		this.somatotype = somatotype;
		this.somatotypeName = this.somatotype.getName();
		this.weight = weight;
		this.diets = new DCList<>();
	}
	
	/**
     * getter del nome profilo
     * 
     * @return 
     *      il nome profilo
     */
	public String getName() {
		return name;
	}
	
	/**
     * setter del nome profilo
     * 
     * @param name 
     *      il nome profilo
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * getter delle calorie di mantenimento del peso corporeo
     * 
     * @return 
     *      le calorie di mantenimento del peso corporeo
     */
	public double getMaintainingKCals() {
		return maintainingKCals;
	}
	
	/**
     * setter delle calorie di mantenimento del peso corporeo
     * 
     * @param 
     *      le calorie di mantenimento del peso corporeo
     */
	public void setMaintainingKCals(double maintainingKCals) {
		this.maintainingKCals = maintainingKCals;
	}
	
	/**
     * aggiunge una dieta alla lista di diete ddel profilo
     * 
     * @param diet  
     *      la dieta da aggiungere
     */
	public void addDiet(Diet diet){
		this.diets.add(diet);
	}
	
	/**
     * getter del peso del profilo
     * 
     * @return 
     *      il peso del profilo
     */
	public double getWeight(){
		return this.weight;
	}
	
	/**
     * getter del somatotipo
     * 
     * @return 
     *      il somatotipo del profilo
     */
	public Somatotype getSomatotype(){
		return this.somatotype;
	}
	
	/**
     * getter del nome del somatotipo del profilo
     * 
     * @return 
     *      il nome del somatotipo del profilo
     */
	public String getSomatotypeName(){
		return this.somatotypeName;
	}
	
	/**
     * getter delle diete del profilo
     * 
     * @return 
     *      la lista di diete del profilo
     */
	public List<Diet> getDiets(){
		return this.diets;
	}
}
