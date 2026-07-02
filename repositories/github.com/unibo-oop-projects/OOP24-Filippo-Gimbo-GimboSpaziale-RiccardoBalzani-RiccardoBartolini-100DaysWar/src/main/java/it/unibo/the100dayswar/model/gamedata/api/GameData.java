package it.unibo.the100dayswar.model.gamedata.api;

import java.io.Serializable;

import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;

/**
 * Interface that model a class to save all the data
 * to load the game.
 */
public interface GameData extends Serializable {
    /**
     * Getter for humanData.
     * 
     * @return the humanData
     */
    HumanPlayer getHumanData();

     /**
     * Getter for botData.
     * 
     * @return the botData
     */
    BotPlayer getBotData();

     /**
     * Getter for mapManager.
     * 
     * @return the mapManager
     */
    MapManager getMapManager();

    /**
     * Getter for gameTurnManager.
     * 
     * @return the gameTurnManager
     */
    GameTurnManager getGameTurnManager();
}
