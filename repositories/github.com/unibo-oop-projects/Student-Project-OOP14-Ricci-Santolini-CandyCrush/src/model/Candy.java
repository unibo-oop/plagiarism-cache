package model;

import controller.Utility;
/**
 * 
 * Classe che modella una caramella del campo di gioco, caratterizzata da un colore e da un tipo.
 * 
 * @author Nicola Santolini
 *
 */
public class Candy implements ICandy {
	
	private int colorNumber;
	private int type;
	
	/**
	 * Costruttore senza parametri.
	 */
	public Candy() {
		this.type = Utility.NORMAL; 
	}
	
	/**
	 * Costruttore con passaggio di parametro.
	 * 
	 * @param i colore con cui inizializzare l'elemento
	 */
	public Candy(final int i) {
		this.colorNumber = i;
		this.type = Utility.NORMAL; 
	}
	
	@Override
	public int getColorNumber() {
		return this.colorNumber;
	}
	
	@Override
	public void setColorNumber(final int c) {
		this.colorNumber = c;
	}
	
	@Override
	public int getType() {
		return this.type;
	}
	
	@Override
	public void setType(final int t) {
		this.type = t;
	}
}
