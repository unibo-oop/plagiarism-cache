package interfaces;

/**
 * interfaccia di PanelRecord
 * 
 * @author Alessandro
 *
 */
public interface PanelRecordInterface extends PanelDifficultInterface{
	
  /**
   * aggiorna la classifica in tempo reale
   */
	public void updateView();
	
	/**
	 * aggiorna la view con il tema scelto
	 */
	public void updateThemeView();

}
