package interfaces;

/**
 * interfaccia di Theme
 * 
 * @author Alessandro
 *
 */
public interface ThemeInterface {
	
  /**
   * imposta il tema chiaro
   */
	public void setWhiteTheme();
	
	/**
	 * imposta il tema scuro
	 */
	public void setBlackTheme();
	
	/**
	 * imposta il tema standard
	 */
	public void setStandardTheme();
	
	/**
	 * 
	 * @return il tema attual come stringa
	 */
	public String getActuallyTheme();
	
	/**
	 * 
	 * @return la stringa del colore primario
	 */
	public String getFirstColor();
	
	/**
	 * 
	 * @return la stringa del colore secondario
	 */
	public String getSecondColor();
	
	/**
	 * 
	 * @return la stringa del colore terziario
	 */
	public String getThirdColor();
	
	/**
	 * imposta il tema non cambiato
	 */
	public void themeNotChanged();
	
	/**
	 * 
	 * @return se il tema è stato recentemente cambiato
	 */
	public boolean isThemeChanged();

}
