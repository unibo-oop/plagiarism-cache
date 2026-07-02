package interfaces;

import board.Cell;

/**
 * interfaccia della Board
 * 
 * @author Alessandro
 *
 */
public interface BoardInterface{
	
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
	 * @return un array di celle
	 */
	public Cell[][] getCells();

}
