package record;

import interfaces.PlayerTimeInterface;

/**
 * classe che gestice i singoli record
 * 
 * @author Alessandro
 *
 */
public class PlayerTime implements PlayerTimeInterface{
	
	private String name;
	private int seconds;
	
	/**
	 * costruttore che imposta i parametri del record
	 * 
	 * @param name
	 *     imposta il nominativo del record
	 * @param seconds
	 *     imposta il tempo del record in secondi
	 */
	public PlayerTime(String name, int seconds) {
		this.name = name;
		this.seconds = seconds;
	}
	
	/**
	 * @return il tempo del record in secondi
	 */
	public int getTime() {
		return this.seconds;
	}
	
	/**
	 * @return il nominativo del record in secondi
	 */
	public String getName() {
		return this.name;
	}

}
