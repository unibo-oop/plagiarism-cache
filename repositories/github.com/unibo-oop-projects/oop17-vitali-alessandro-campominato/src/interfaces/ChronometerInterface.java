package interfaces;

/**
 * interfaccia di Chronometer
 * 
 * @author Alessandro
 *
 */
public interface ChronometerInterface {
	
  /**
   * riavvia il cronometro
   */
	public void restartChronometer();
	
	/**
	 * 
	 * @return se il cronometro è già avviato
	 */
	public boolean hasTimerStarted();
	
	/**
	 * 
	 * @return il tempo impiegato in secondi
	 */
	public int getTime();

}
