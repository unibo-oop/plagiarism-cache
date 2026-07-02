package utils;

/**
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it (Mat. 0000718877)
 *
 * Classe che rappresenta la posizione dell'attrezzatura
 */
public class Position {

	/**
	 * Rappresenta la fila 
	 */
	private int row;
	
	/**
	 * Rappresenta il numero rispetto alla fila
	 */
	private int number;
	
	/**
	 * Costruttore della posizione
	 * @param row
	 * @param number
	 */
	public Position(final int row, final int number) {
		this.row = row;
		this.number = number;
	}
	
	/**
	 * 
	 * Costruttore utilizzato per ritornare una copia dell'oggetto 
	 * @param position
	 */
	public Position(Position position) {
		this.row = position.getRow();
		this.number = position.getNumber();
	}
	
	/**
	 * @return Copia dell'ogetto posizione con i relativi valori
	 */
	public Position getCoordinates() {
		return new Position(this.row, this.number);
	}
	
	/**
	 * 
	 * @return valore della fila
	 */
	public int getRow() {
		return new Integer(this.row);
	}
	
	/**
	 * 
	 * @return numero rispetto alla fila
	 */
	public int getNumber() {
		return new Integer(this.number);
	}
}
