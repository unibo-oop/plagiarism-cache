package view.interfaces;

import controller.GameController;
import model.games.Game;

public interface GameView extends View<Game> {

	/**
	 * set The Game Contoller
	 * @param controller Game Controller
	 */
	public void setController(GameController controller);
	
	/**
	 * initialize the UI structure based on the Game
	 * @param game Game to load
	 */
	public void initialize(Game game);
}
