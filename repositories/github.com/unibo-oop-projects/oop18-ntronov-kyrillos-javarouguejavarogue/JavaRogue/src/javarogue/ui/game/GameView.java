package javarogue.ui.game;

/**
 * 
 * View of the Game Window, renders the game graphics.
 *
 */
public interface GameView {

	/**
	 * 
	 * Links Controller
	 * 
	 * @param controller
	 */
	public void setController(GameController controller);
	
	/**
	 *  Re-draws graphics, should be called after every game step (tick).
	 */
	public void render();
	
	/**
	 *  Opens the game UI.
	 */
	public void open();
	
	/**
	 *  Closes the game UI.
	 */
	public void close();
	
}
