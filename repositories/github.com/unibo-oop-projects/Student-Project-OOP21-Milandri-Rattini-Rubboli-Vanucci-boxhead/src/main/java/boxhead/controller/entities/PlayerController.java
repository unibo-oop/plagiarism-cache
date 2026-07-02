package boxhead.controller.entities;

import boxhead.model.entities.Player;
import boxhead.view.entities.PlayerView;

/**
 * Interface to model the {@link Player} Controller.
 */
public interface PlayerController {
	
	/**
	 * Return player model
	 * @return Player
	 */
	Player getPlayer();
	
	/**
	 * Return player view
	 * @return PlayerView
	 */
	PlayerView getPlayerView();
	
	/**
	 * Update view and model
	 */
	void update();
	
	/**
	 * Update based on keyboard input
	 */
	void updateInput();
}



