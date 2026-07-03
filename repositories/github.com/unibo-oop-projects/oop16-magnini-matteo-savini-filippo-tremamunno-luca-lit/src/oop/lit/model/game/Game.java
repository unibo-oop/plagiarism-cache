package oop.lit.model.game;

import java.util.List;

import oop.lit.model.Action;
import oop.lit.model.groups.Board;
import oop.lit.model.groups.GroupViewer;
import oop.lit.util.Observable;
import oop.lit.view.ViewRequests;

/**
 * Main model interface.
 * Notifies observers when possible actions that can be performed on the game change.
 *
 * @param <B> the type of board this game uses.
 * @param <P> the type of PlayerManager this game uses.
 */
public interface Game<B extends Board<?>, P extends PlayerManager<?, ?>> extends Observable {
    /**
     * @return
     *      this game board.
     */
    B getBoard();

    /**
     * @return
     *      this game player manager.
     */
    P getPlayerManager();

    /**
     * @return
     *      this game group viewer.
     */
    GroupViewer getGroupViewer();

    /**
     * @return
     *      all action that can be performed on this game.
     */
    List<Action> getActions();

    /**
     * Sets the game View.
     * To be used after deserialization.
     * @param view
     *      the used view.
     */
    void setView(ViewRequests view);
}