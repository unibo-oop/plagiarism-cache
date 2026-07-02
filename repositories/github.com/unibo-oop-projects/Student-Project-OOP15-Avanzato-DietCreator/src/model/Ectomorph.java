package model;

import java.util.Arrays;

/**
 * Classe ectomorfo che estende la classe Somatotype
 */

public class Ectomorph extends Somatotype {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] traitsArray = new String[]{"linear", "low fat", "skinny", "usually tall", "flat chest",
	        "small joints", "narrow hips and clavicles", "long limbs"};
	
	/**
     * Costruttore della classe ectomorfo.
     * Inizializza i campi della classe astratta Somatotype
     */
	public Ectomorph(){
		
		this.name = "Ectomorph";
		
		//lose percentages
		this.loseMinCarbs = 30;
		this.loseMaxCarbs = 45;
		this.loseMinFats = 20;
		this.loseMaxFats = 40;
		this.loseMinProts = 25;
		this.loseMaxProts = 40;
		
		//maintain percentages
		this.maintainMinCarbs = 45;
		this.maintainMaxCarbs = 55;
		this.maintainMinFats = 10;
		this.maintainMaxFats = 35;
		this.maintainMinProts = 25;
		this.maintainMaxProts = 40;
		
		//gain percentages
		this.gainMinCarbs = 55;
		this.gainMaxCarbs = 60;
		this.gainMinFats = 10;
		this.gainMaxFats = 30;
		this.gainMinProts = 25;
		this.gainMaxProts = 40;
		
		traits = Arrays.asList(traitsArray);

	}
	
}
