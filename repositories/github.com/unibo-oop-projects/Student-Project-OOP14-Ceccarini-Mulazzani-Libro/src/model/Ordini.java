package model;

/**
 * 
 * @author Chiara Ceccarini
 *
 */
public class Ordini extends BasicOperations implements IOrdini {
	
	/**
	 * 
	 * @param book to remove
	 */
	public void remove(final Libro book) {
		super.libreria.remove(book);
	}

	/**
	 * 
	 */
	public void evasioneOrdini() {
		super.libreria.clear();
	}

}
