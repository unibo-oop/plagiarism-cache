package other;

import interfaces.DifficultInterface;

/**
 * gestione della difficolt� del gioco
 * 
 * @author Alessandro
 *
 */
public class Difficult implements DifficultInterface{
	
	private String difficultGame;
	
	private int rows;
	private int columns;
	private int bombs;
	
	private boolean changeDifficult = false;
	
	/**
	 * costruttore che imposta di default la difficolt� base
	 */
	public Difficult() {
		this.setDifficultEasy();
	}
	
	/**
	 * imposta il numero di righe, colonne, bombe e la stringa in difficolt� Easy
	 */
	public void setDifficultEasy() {
		if(this.difficultGame == null) {
			this.difficultGame = "Easy";
			this.rows = 8;
			this.columns = 8;
			this.bombs = 10;
		} else if(this.difficultGame != "Easy") {
			this.difficultGame = "Easy";
			this.rows = 8;
			this.columns = 8;
			this.bombs = 10;
			this.changeDifficult = true;
		}
	}
	
	 /**
   * imposta il numero di righe, colonne, bombe e la stringa in difficolt� Medium
   */
	public void setDifficultMedium() {
		if(this.difficultGame != "Medium") {
			this.difficultGame = "Medium";
			this.rows = 10;
			this.columns = 14;
			this.bombs = 20;
			this.changeDifficult = true;
		}
	}
	
	 /**
   * imposta il numero di righe, colonne, bombe e la stringa in difficolt� Hard
   */
	public void setDifficultHard() {
		if(this.difficultGame != "Hard") {
			this.difficultGame = "Hard";
			this.rows = 13;
			this.columns = 20;
			this.bombs = 40;
			this.changeDifficult = true;
		}
	}
	
	 /**
   * imposta il numero di righe, colonne, bombe e la stringa in difficolt� Custom
   * 
   * @param rows
   *    numero di righe da impostare ricevuto dall'utente
   * @param columns
   *    numero di colonne da impostare ricevuto dall'utente
   * @param bombs
   *    numero di bombe da impostare ricevuto dall'utente
   */
	public void setDifficultCustom(int rows, int columns, int bombs) {
			this.difficultGame = "Custom";
			this.rows = rows;
			this.columns = columns;
			this.bombs = bombs;
			this.changeDifficult = true;
	}
	
	/**
	 * @return la difficolt� attuale come stringa
	 */
	public String getDifficult() {
		return this.difficultGame;
	}
	
	/**
	 * @return il numero di righe
	 */
	public int getRows() {
		return this.rows;
	}

	/**
	 * @return il numero di colonne
	 */
	public int getColumns() {
		return this.columns;
	}
	
	/**
	 * @return il numero di bombe
	 */
	public int getBombs() {
		return this.bombs;
	}
	
	/**
	 * @return se la difficolt� � stata recentemente cambiata
	 */
	public boolean isChange() {
		return this.changeDifficult;
	}
	
	/**
	 * imposta la difficolt� come non modificata recentemente
	 */
	public void setNotChange() {
		this.changeDifficult = false;
	}
}
