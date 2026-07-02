package model;

import java.util.Arrays;

/**
 * Classe mesomorfo che estende la classe Somatotype
 */
public class Mesomorph extends Somatotype{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] traitsArray = new String[]{"athletic body","gains muscle easily", "gains fat easily than ectomorph",
            "large shoulders", "narrow waist", "thinner joints", "wide clavicles"};
    
	
	/**
     * Costruttore della classe mesomorfo.
     * Inizializza i campi della classe astratta Somatotype
     */
	public Mesomorph () {
		
		this.name = "Mesomorph";
		
		//lose percentages
		this.loseMinCarbs = 20;
		this.loseMaxCarbs = 30;
		this.loseMinFats = 20;
		this.loseMaxFats = 40;
		this.loseMinProts = 25;
		this.loseMaxProts = 50;
		
		//maintain percentages
		this.maintainMinCarbs = 30;
		this.maintainMaxCarbs = 40;
		this.maintainMinFats = 10;
		this.maintainMaxFats = 40;
		this.maintainMinProts = 25;
		this.maintainMaxProts = 50;
		
		//gain percentages
		this.gainMinCarbs = 40;
		this.gainMaxCarbs = 50;
		this.gainMinFats = 10;
		this.gainMaxFats = 35;
		this.gainMinProts = 25;
		this.gainMaxProts = 50;
		
		traits = Arrays.asList(traitsArray);
		
	}
	
}
