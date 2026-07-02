package controller.command;

import model.Player;
import utility.Pair;

/**
 * Stop position on key release
 * @author denis
 *
 */
public class Stop implements Command{
	
	/**
	 * Set the speed on 0 to stop the player on key release
	 */
	@Override
	public void execute(Player player) {
		player.setSpeed(new Pair<>(0,0));
		player.updatePosition();
	}
	
	
}
