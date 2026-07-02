package model;

import java.util.Arrays;

/**
 * Classe endomorfo che estende la classe Somatotype
 */
public class Endomorph extends Somatotype{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] traitsArray = new String[]{"usually short", "thick rib cage", "wide/thicker joints", "hips as wide as clavicles",
	        "shorter limbs"};
	/**
     * Costruttore della classe endomorfo.
     * Inizializza i campi della classe astratta Somatotype
     */
	public Endomorph(){
		
		this.name = "Endomorph";
		
		//lose percentages
		this.loseMinCarbs = 10;
		this.loseMaxCarbs = 20;
		this.loseMinFats = 20;
		this.loseMaxFats = 40;
		this.loseMinProts = 25;
		this.loseMaxProts = 50;
		
		//maintain percentages
		this.maintainMinCarbs = 20;
		this.maintainMaxCarbs = 30;
		this.maintainMinFats = 20;
		this.maintainMaxFats = 40;
		this.maintainMinProts = 25;
		this.maintainMaxProts = 50;
		
		//gain percentages
		this.gainMinCarbs = 30;
		this.gainMaxCarbs = 40;
		this.gainMinFats = 10;
		this.gainMaxFats = 35;
		this.gainMinProts = 25;
		this.gainMaxProts = 50;
		
		traits = Arrays.asList(traitsArray);
		
	}
	
}
