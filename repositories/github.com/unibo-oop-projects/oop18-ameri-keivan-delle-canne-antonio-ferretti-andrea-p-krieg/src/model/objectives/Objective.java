package model.objectives;

import model.map.ObservableGameMap;
import model.player.Player;

/**
 * Models the objectives of the game, that are the way for the players to win.
 */
public interface Objective {
    /**
     * 
     * @return the description of the objective
     */
    String getDescription();

    /**
     * At the end of each turn a player can win if he has completed his objective.
     * This method watches the map and the player status to check if he won.
     * 
     * @param actualGameMap the GameMap at the current state
     * 
     * @param player        the player to check if he completed his objective
     * 
     * @return whether the player won the game
     * 
     */
    boolean isCompleted(ObservableGameMap actualGameMap, Player player);
}
