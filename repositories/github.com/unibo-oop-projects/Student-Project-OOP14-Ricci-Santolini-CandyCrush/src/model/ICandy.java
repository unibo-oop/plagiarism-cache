package model;

/**
 * 
 * Interfaccia che modella una caramella, un singolo elemento della matrice di gioco.
 * 
 * @author Nicola Santolini
 *
 */
public interface ICandy {

	/**
	 * Getter del colore dell'elemento.
	 * 
	 * @return il colore dell'elemento
	 */
	int getColorNumber();
	
	/**
	 * Setter del colore di un elemento.
	 * 
	 * @param c	il colore da applicare a un elemento
	 */
	void setColorNumber(final int c);
	
	/**
	 * Getter del tipo dell'elemento.
	 * 
	 * @return il tipo dell'elemento
	 */
	int getType();
	
	/**
	 * Setter del tipo dell'elemento.
	 * 
	 * @param t il tipo da applicare a un elemento
	 */
	void setType(final int t);
}
