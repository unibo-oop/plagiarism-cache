package model;

import java.io.Serializable;
import java.util.List;

/**
 * Classe astratta somatotipo
 */
public abstract class Somatotype implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected List<String> traits;
		
	//lose weight percentages
	protected int loseMinCarbs;
	protected int loseMaxCarbs;
	protected int loseMinFats;
	protected int loseMaxFats;
	protected int loseMinProts;
	protected int loseMaxProts;
	
	//maintain weight percentages
	protected int maintainMinCarbs;
	protected int maintainMaxCarbs;
	protected int maintainMinFats;
	protected int maintainMaxFats;
	protected int maintainMinProts;
	protected int maintainMaxProts;
	
	//gain weight percentages
	protected int gainMinCarbs;
	protected int gainMaxCarbs;
	protected int gainMinFats;
	protected int gainMaxFats;
	protected int gainMinProts;
	protected int gainMaxProts;
	
	public String getName(){
		return this.name;
	}
	
	//lose
	
	/**
     * getter della percentuale minima di carboidrati per perdere peso di un somatotipo
     * 
     * @return
     *      la percentuale minima di carboidrati per perdere peso
     */
	public int getLoseMinCarbs() {
		return loseMinCarbs;
	}
	
	/**
     * getter della percentuale massima di carboidrati per perdere peso di un somatotipo
     * 
     * @return
     *      la percentuale massima di carboidrati per perdere peso
     */
	public int getLoseMaxCarbs() {
		return loseMaxCarbs;
	}
	
	/**
     * getter della percentuale minima di grassi per perdere peso di un somatotipo
     * 
     * @return
     *      la percentuale minima di grassi per perdere peso
     */
	public int getLoseMinFats() {
		return loseMinFats;
	}
	
	/**
     * getter della percentuale massima di grassi per perdere peso di un somatotipo
     * 
     * @return
     *      la percentuale massima di grassi per perdere peso
     */
	public int getLoseMaxFats() {
		return loseMaxFats;
	}
	
	/**
     * getter della percentuale minima di proteine per perdere peso di un somatotipo
     * 
     * @return
     *      la percentuale minima di proteine per perdere peso
     */
	public int getLoseMinProts() {
		return loseMinProts;
	}
	
	/**
     * getter della percentuale massima di proteine per perdere peso di un somatotipo
     * 
     * @return
     *      la percentuale minima di proteine per perdere peso
     */
	public int getLoseMaxProts() {
		return loseMaxProts;
	}
	
	//maintain
	
	/**
     * getter della percentuale minima di carboidrati per mantenere il peso di un somatotipo
     * 
     * @return
     *      la percentuale minima di carboidrati per mantenere il peso
     */
	public int getMaintainMinCarbs() {
		return maintainMinCarbs;
	}
	
	/**
     * getter della percentuale massima di carboidrati per mantenere il peso di un somatotipo
     * 
     * @return
     *      la percentuale massima di carboidrati per mantenere il peso
     */
	public int getMaintainMaxCarbs() {
		return maintainMaxCarbs;
	}
	
	/**
     * getter della percentuale minima di grassi per mantenere il peso di un somatotipo
     * 
     * @return
     *      la percentuale minima di grassi per mantenere il peso
     */
	public int getMaintainMinFats() {
		return maintainMinFats;
	}
	
	/**
     * getter della percentuale massima di grassi per mantenere il peso di un somatotipo
     * 
     * @return
     *      la percentuale massima di grassi per mantenere il peso
     */
	public int getMaintainMaxFats() {
		return maintainMaxFats;
	}
	
	/**
     * getter della percentuale minima di proteine per mantenere il peso di un somatotipo
     * 
     * @return
     *      la percentuale minima di proteine per mantenere il peso
     */
	public int getMaintainMinProts() {
		return maintainMinProts;
	}
	
	/**
     * getter della percentuale massima di proteine per mantenere il peso di un somatotipo
     * 
     * @return
     *      la percentuale massima di proteine per mantenere il peso
     */
	public int getMaintainMaxProts() {
		return maintainMaxProts;
	}
	
	//gain
	
	/**
     * getter della percentuale minima di carboidrati per aumentare il peso di un somatotipo
     * 
     * @return
     *      la percentuale minima di carboidrati per aumentare il peso
     */
	public int getGainMinCarbs() {
		return gainMinCarbs;
	}
	
	/**
     * getter della percentuale massima di carboidrati per aumentare il peso di un somatotipo
     * 
     * @return
     *      la percentuale massima di carboidrati per aumentare il peso
     */
	public int getGainMaxCarbs() {
		return gainMaxCarbs;
	}
	
	/**
     * getter della percentuale minima di grassi per aumentare il peso di un somatotipo
     * 
     * @return
     *      la percentuale minima di grassi per aumentare il peso
     */
	public int getGainMinFats() {
		return gainMinFats;
	}
	
	/**
     * getter della percentuale massima di grassi per aumentare il peso di un somatotipo
     * 
     * @return
     *      la percentuale massima di grassi per aumentare il peso
     */
	public int getGainMaxFats() {
		return gainMaxFats;
	}
	
	/**
     * getter della percentuale minima di proteine per aumentare il peso di un somatotipo
     * 
     * @return
     *      la percentuale minima di proteine per aumentare il peso
     */
	public int getGainMinProts() {
		return gainMinProts;
	}
	
	/**
     * getter della percentuale massima di proteine per aumentare il peso di un somatotipo
     * 
     * @return
     *      la percentuale massima di proteine per aumentare il peso
     */
	public int getGainMaxProts() {
		return gainMaxProts;
	}
	
	/**
     * getter della lista di tratti di un somatotipo
     * 
     * @return
     *      la lista di tratti di un somatotipo
     */
	public List<String> getTraits(){
		return this.traits;
	}

}
