package interfaces;

import enumeration.Action;

/**
 * interfaccia del BoardGameView
 * 
 * @author Alessandro
 *
 */
public interface BoardGameViewInterface extends PanelDifficultInterface {
	
  /**
   * gestisce il click sulla view
   * 
   * @param object
   *    oggetto sul quale è stato effettuato il click
   * @param action
   *    azione da applicare al click
   */
	public void clickView(Object object, Action action);
	
	/**
	 * funzione che aggiorna la view in base al tema scelto
	 */
	public void updateThemeView();
	

}
