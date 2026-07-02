package it.unibo.the100dayswar.model.bot.api;

import java.util.Set;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.player.api.Player;

/**
 * Interface that rapresents a bot player in the game.
 */
public interface BotPlayer extends Player {
    /** 
     * The method that will update the bot's state and make the move 
     * according to the current strategy.
     */
    void makeMove();
    /** 
     * The method that is used to get the spawn point of the enemy.
     * 
     * @return the spawn point of the enemy
     */
    Cell getEnemySpawnPoint();
    /** 
     * The method that is used to get all the cells in the game map.
     * 
     * @return a set of all the cells in the game map
     */
    Set<Cell> getAllCells();
    /**
     * Getter for mapManager.
     * 
     * @return the mapManager
     */
    MapManager getMapManager();
    /**
     * Getter for Strategy.
     * 
     * @return the strategy
     */
    BotStrategy getStrategy();
}
