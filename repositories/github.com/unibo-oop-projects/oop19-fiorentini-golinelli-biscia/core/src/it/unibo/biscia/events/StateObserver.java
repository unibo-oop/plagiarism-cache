package it.unibo.biscia.events;

import it.unibo.biscia.core.Entity;
import it.unibo.biscia.core.Level;
import it.unibo.biscia.core.Player;

import java.util.List;

/**
 * event listener for general state of game.
 *
 */
public interface StateObserver {

    /**
     * fired when the game is over.
     */
    void gameOver();

    /**
     * fired when the game goto pause.
     */
    void gamePause();

    /**
     * fired when the game is resumed.
     */
    void gameResume();

    /**
     * fired when starts new level. before of this events is fired every time a
     * pause. this event is fired also on start of first level
     * 
     * @param level the new level
     */
    void newLevel(Level level);

    /**
     * fired when some entities moved.
     * 
     * @param entities the subject of event
     */
    void update(List<Entity> entities);

    /**
     * fired when some entities removed.
     * 
     * @param entities list of entities removed
     */
    void remove(List<Entity> entities);

    /**
     * fired when some entities added.
     * 
     * @param entities list of entities added
     */
    void add(List<Entity> entities);

    /**
     * fired when player lives or point is changed.
     * 
     * @param player player to change
     */
    void updatePlayer(Player player);

}
