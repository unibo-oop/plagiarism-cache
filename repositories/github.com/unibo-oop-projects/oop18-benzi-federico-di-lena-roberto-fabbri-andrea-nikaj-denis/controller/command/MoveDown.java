package controller.command;

import model.Player;
import utility.Pair;

/**
 * Move down class
 * @author denis
 *
 */
public class MoveDown implements Command{

	private static final int MOVE_DOWN = 7;

	/** Moves the car to the right.
	 * 
	 * @param Player
	 *          
	 */
	@Override
	public void execute(Player player) {
		player.setSpeed(new Pair<>(0,MOVE_DOWN));
		player.updatePosition();
	}
	
	
}
