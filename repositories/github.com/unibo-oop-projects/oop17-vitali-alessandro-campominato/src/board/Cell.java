package board;

import interfaces.CellInterface;

/**
 * Elemento cella della Board
 * 
 * @author Alessandro
 *
 */
public class Cell implements CellInterface{
	
	private boolean revealed = false;
	private boolean bomb;
	private boolean flag = false;
	private Cell[] neighbors = {};
	
	private int x;
	private int y;
	
	/**
	 * costruttore che crea la cella impostandola come bomba oppure no
	 * 
	 * @param bomb
	 *   se è bomba o no
	 */
	public Cell(boolean bomb) {
		this.bomb = bomb;
	}
	
	/**
	 * inserisce un array di celle confinanti
	 * 
	 * @param neighbors
	 *   array di celle confinanti
	 */
	public void setNeighbors(Cell[] neighbors) {
		this.neighbors = neighbors;
	}
	
	/**
	 * 
	 * @return un array di celle confinanti
	 */
	public Cell[] getNeighbors() {
		return this.neighbors;
	}

	/**
	 * 
	 * @return il numero di celle confinanti
	 */
	public int numberOfNeighbors() {
		int count = 0;
		for(@SuppressWarnings("unused") Cell neighbor : this.neighbors) {
			count++;
		}
		return count;
	}
	
	/**
	 * 
	 * @return il numero di celle bomba confinanti
	 */
	public int getNumberNeighborBombs() {
		int nOfNeighborBombs = 0;
		for(Cell neighbor : this.neighbors) {
			if(neighbor.isBomb()) {
				nOfNeighborBombs++;
			}
		}
		return nOfNeighborBombs;
	}
	
	/**
	 * 
	 * @return se la cella è una bomba
	 */
	public boolean isBomb() {
		if(this.bomb) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * imposta una cella come scoperta
	 */
	public void setRevealed() {
		this.revealed = true;
	}
	
	/**
	 * 
	 * @return se la cella è scoperta
	 */
	public boolean isRevealed() {
		return this.revealed;
	}
	
	/**
	 * 
	 * @param x
	 *   la coordinata x della cella
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * 
	 * @param y
	 *   la coordinata y della cella
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * 
	 * @return la coordinata x della cella
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * 
	 * @return la coordinata y della cella
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * imposta la bandiera nella cella
	 */
	public void addFlag() {
		this.flag = true;
	}
	
	/**
	 * rimuove la bandiera dalla cella
	 */
	public void removeFlag() {
		this.flag = false;
	}
	
	/**
	 * 
	 * @return se la cella è una bandiera
	 */
	public boolean isFlag() {
		return this.flag;
	}

}
