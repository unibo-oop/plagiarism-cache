package view.gameview;

import java.util.List;

import view.inputhandler.Action;

/**
 * Main GameView Controller. It has to receive from keyboard inputs for the
 * {@link Hero}, pass them to the {@link Model} which elaborate them and then
 * update itself.
 */
public interface GameView {

    /**
     * Return the list of the key pressed.
     * 
     * @return the list of {@link Action}s done by the player.
     */
    List<Action> getExecutedActions();

    /**
     * Call calculateScale and update methods.
     */
    void reset();

    /**
     * Draw or redraw only dynamic entities (Movable entities).
     */
    void update();

    /**
     * Draw or redraw all entities.
     */
    void updateAll();
}
