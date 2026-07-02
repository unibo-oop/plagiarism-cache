package controller.command;

import model.Player;
import utility.Pair;

/**
 * Move right position
 * @author denis
 *
 */
public class MoveRight implements Command{
	
private static final int MOVE_RIGHT = 7;

	/**
	 * Moves the car to the right.
	 * 
	 * @param Player
	 *          
	 */
	@Override
	public void execute(Player player) {
		player.setSpeed(new Pair<>(MOVE_RIGHT,0));
		player.updatePosition();
	}
	
}
