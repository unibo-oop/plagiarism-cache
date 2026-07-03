package oop.lit.model.simplegame.actions;

import java.io.Serializable;

import oop.lit.model.Action;

/**
 * An action factory for a simple game.
 * The canBePerformed method result of the actions provided by this factory depends only on the provided playerManager state.
 */
public interface SimpleGameActionFactory extends Serializable {

    /**
     * @return
     *      an action used to add a player to the player manager; automatically adds an hand to the player.
     */
    Action getAddPlayerAction();

    /**
     * @return
     *      an action used to remove a player from the player manager.
     */
    Action getRemovePlayerAction();

    /**
     * @return
     *      an action used to load an image in the ImageLoader.
     */
    Action getLoadImageAction();

    /**
     * @return
     *      a show hand action; if the playingPlayer is not a gm shows is hand (adds it to the group viewer).
     *      if the playingPlayer is a gm, asks which player hand is to be shown, then shows it.
     */
    Action getShowHandAction();

    /**
     * @return
     *      an action used to set which player turn it is.
     */
    Action getSetTurnAction();

    /**
     * @return
     *      an action used to add a BasicSBE to the board.
     */
    Action getAddBasicElementAction();

    /**
     * @return
     *      an action used to add a GroupSBE to the board.
     */
    Action getAddGroupElementAction();

    /**
     * @return
     *      an action used to add a FlippableSBE to the board.
     */
    Action getAddFlippableElementAction();

    /**
     * @return
     *      an action used to change the playingPlayer.
     */
    Action getChangePlayerAction();

    /**
     * @return
     *      an action used to save the game status
     */
    Action getSaveAction();

    /**
     * @return
     *      an action used to import elements from another simple game.
     */
    Action getImportAction();

}