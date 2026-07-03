package view.interfaces;

import controller.ConfigController;
import model.games.Game;

/**
 * 
 * @author Stefano Benelli
 * This is the basic representation of ConfigView
 */
public interface ConfigView extends View<Game> {
	
	/**
	 * set The Config Contoller
	 * @param controller Config Controller
	 */
	public void setController(ConfigController controller);
}
