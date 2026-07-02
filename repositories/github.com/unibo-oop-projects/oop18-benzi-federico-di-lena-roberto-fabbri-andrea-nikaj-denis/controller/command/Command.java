package controller.command;

import model.Player;

/**
 * Main interface of Command pattern
 * @author denis
 *
 */
public interface Command {
	
	 /**
     * Executes a command.
     * 
     * @param player
     *          Player model
     */
	public void execute(Player player);
}
