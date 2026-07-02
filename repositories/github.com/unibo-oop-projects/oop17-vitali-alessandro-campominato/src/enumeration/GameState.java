package enumeration;

/**
 * enumeration per gestire lo stato della partita
 * 
 * @author Alessandro
 *
 */
public enum GameState {
	
  /**
   * la partita sta procedendo
   */
	ONGOING, 
	/**
	 * la partita deve ancora iniziare
	 */
	STOP, 
	/**
	 * vittoria
	 */
	WON, 
	/**
	 * sconfitta
	 */
	LOST

}
