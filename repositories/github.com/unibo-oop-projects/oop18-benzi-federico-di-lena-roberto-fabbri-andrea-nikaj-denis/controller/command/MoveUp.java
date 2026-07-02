package controller.command;

import model.Player;
import utility.Pair;

/**
 * Move up position
 * @author denis
 *
 */
public class MoveUp implements Command {

	private static final int MOVE_UP = -7;

	/**
	 * Moves the car to the right.
	 * 
	 * @param Player
	 *          
	 */
	@Override
	public void execute(Player player) {
		player.setSpeed(new Pair<>(0,MOVE_UP));
		player.updatePosition();
	}
	
	
}
