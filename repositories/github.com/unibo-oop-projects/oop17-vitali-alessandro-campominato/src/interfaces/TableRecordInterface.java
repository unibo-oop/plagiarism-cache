package interfaces;

import record.PlayerTime;

/**
 * interfaccia di TableRecord
 * 
 * @author Alessandro
 *
 */
public interface TableRecordInterface {
	
  /**
   * aggiunge un record
   * 
   * @param name
   *    nome riferito al record
   * @param seconds
   *    secondi del record
   */
	public void addRecord(String name, int seconds);
	
	/**
	 * controlla che il tempo sia un record
	 * 
	 * @param seconds
	 *     secondi del record
	 * @return se è record oppure no
	 */
	public boolean isRecord(int seconds);
	
	/**
	 * 
	 * @return un array con i record
	 */
	public PlayerTime[] getListRecord();
	
	/**
	 * 
	 * @return la difficoltà di quel record
	 */
	public String getTableDifficult();

}
