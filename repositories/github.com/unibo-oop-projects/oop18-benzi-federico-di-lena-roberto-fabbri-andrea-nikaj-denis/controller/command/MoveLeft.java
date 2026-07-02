package controller.command;


import model.Player;
import utility.Pair;

/**
 * Move Left position
 * @author denis
 *
 */
public class MoveLeft implements Command{
	
	private static final int MOVE_LEFT = -7;

	 /**
     * Moves the car to the left.
     * 
     * @param Player
     *          
     */
	@Override
	public void execute(Player player) {
		player.setSpeed(new Pair<>(MOVE_LEFT,0));
		player.updatePosition();
	}

}
