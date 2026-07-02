package pvz.view.gameview.api;

import pvz.controller.gamecontroller.api.ViewListener;
import pvz.utilities.GameEntity;

import java.util.Set;

/**
 * Interface representing the game view in the MVC architecture.
 * It defines the methods needed to render the game state and interact with user inputs.
 */
public interface GameView {
    /**
     * Closes the game view and releases its resources.
     */
    void close();

    /**
     * Renders the current game state, including entities and stats.
     *
     * @param entities the set of all current game entities to draw
     * @param suns     the current sun (currency) count
     * @param kills    the total number of zombies killed
     */
    void render(Set<GameEntity> entities, int suns, int kills);

    /**
     * Sets the listener that handles user input from the view.
     *
     * @param listener the listener to notify of user interactions
     */
    void setViewListener(ViewListener listener);
}
