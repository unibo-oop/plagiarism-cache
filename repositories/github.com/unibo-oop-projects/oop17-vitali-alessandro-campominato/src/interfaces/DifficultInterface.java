package interfaces;

/**
 * interfaccia di Difficult
 * 
 * @author Alessandro
 *
 */
public interface DifficultInterface {

  /**
   * imposta la difficoltà facile
   */
	public void setDifficultEasy();
	
	/**
	 * imposta la difficoltà media
	 */
	public void setDifficultMedium();
	
	/**
	 * imposta la difficoltà difficile
	 */
	public void setDifficultHard();
	
	/**
	 * imposta una difficoltà personalizzata con righe, colonne e bombe scelte dall'utente
	 * 
	 * @param rows
	 *     il numero di righe desiderato
	 * @param columns
	 *     il numero di colonne desiderato
	 * @param bombs
	 *     il numero di bombe desiderato
	 */
	public void setDifficultCustom(int rows, int columns, int bombs);
	
	/**
	 * 
	 * @return la difficoltà attualmente impostata
	 */
	public String getDifficult();
	
	/**
	 * 
	 * @return il numero di righe
	 */
	public int getRows();
	
	/**
	 * 
	 * @return il numero di colonne
	 */
	public int getColumns();
	
	/**
	 * 
	 * @return il numero di bombe
	 */
	public int getBombs();
	
	/**
	 * 
	 * @return se la difficoltà è cambiata
	 */
	public boolean isChange();
	
	/**
	 * imposta la difficoltà non cambiata
	 */
	public void setNotChange();
	
}
