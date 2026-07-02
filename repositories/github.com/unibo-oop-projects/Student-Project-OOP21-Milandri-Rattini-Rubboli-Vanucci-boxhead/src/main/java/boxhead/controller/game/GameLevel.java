package boxhead.controller.game;

import java.util.Set;

import boxhead.controller.entities.InputHandler;
import boxhead.controller.entities.PlayerController;
import boxhead.controller.entities.ShotController;
import boxhead.controller.entities.ZombieController;
import boxhead.model.score.Score;
import boxhead.view.GameView;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

/**
 * Interface to assemble all the controllers of the game.
 */
public interface GameLevel {
	
	/**
	 * Main method to update the game.
	 */
	void handle();
	/**
	 * @return
	 * 			The InputHandler of the game.
	 */
	InputHandler getInputHandler();
	/**
	 * @return
	 * 			The PlayerController of the game.
	 */
	PlayerController getPlayerController();
	/**
	 * @return
	 * 			The ZombieController of the game.
	 */
	ZombieController getZombieController();
	/**
	 * @return
	 * 			The ShotController of the game.
	 */
	ShotController getShotController();
	/**
	 * @return
	 * 			The GameView of the game.
	 */
	GameView getGameView();
	/**
	 * @return
	 * 			The player's position.
	 */
	Point2D getPlayerPos();
	/**
	 * @return
	 * 			A set with all the BoundingBoxes of the walls.
	 */
	Set<BoundingBox> getWalls();
	/**
	 * @return
	 * 			True if the player is still in life.
	 */
	boolean isPlayerAlive();
	/**
	 * @return
	 * 			The Score associated with the game.
	 */
	Score getScore();
}
